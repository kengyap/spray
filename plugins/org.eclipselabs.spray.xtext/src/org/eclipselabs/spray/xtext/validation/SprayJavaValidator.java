/** ****************************************************************************
 * Copyright (c)  The Spray Project.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Spray Dev Team - initial API and implementation
 **************************************************************************** */
package org.eclipselabs.spray.xtext.validation;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.validation.Check;
import org.eclipselabs.spray.mm.spray.AliasableElement;
import org.eclipselabs.spray.mm.spray.Behavior;
import org.eclipselabs.spray.mm.spray.ConnectionInSpray;
import org.eclipselabs.spray.mm.spray.CreateBehavior;
import org.eclipselabs.spray.mm.spray.Diagram;
import org.eclipselabs.spray.mm.spray.Import;
import org.eclipselabs.spray.mm.spray.MetaClass;
import org.eclipselabs.spray.mm.spray.MetaReference;
import org.eclipselabs.spray.mm.spray.ShapeCompartmentAssignment;
import org.eclipselabs.spray.mm.spray.ShapeDslKey;
import org.eclipselabs.spray.mm.spray.ShapePropertyAssignment;
import org.eclipselabs.spray.mm.spray.SprayPackage;
import org.eclipselabs.spray.xtext.scoping.PackageSelector;
import org.eclipselabs.spray.xtext.util.GenModelHelper;
import org.eclipselabs.spray.xtext.util.TextBodyFetcher;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import javax.inject.Inject;

public class SprayJavaValidator extends AbstractSprayJavaValidator implements IssueCodes {
    @Inject
    private GenModelHelper           genModelHelper;
    @Inject
    private IJvmTypeProvider.Factory typeProviderFactory;
    @Inject
    private PackageSelector          packageSelector;
    @Inject
    private TextBodyFetcher          textBodyFetcher;

    /**
     * Add additional EReferences for type conformance validation of expressions.
     */
    //    @Override
    //    protected Set<EReference> getTypeConformanceCheckedReferences() {
    //        Set<EReference> references = new HashSet<EReference>(super.getTypeConformanceCheckedReferences());
    //        references.add(SprayPackage.Literals.SHAPE_PROPERTY_ASSIGNMENT__VALUE);
    //        return references;
    //    }

    @Check
    public void checkGenModelAvailable(MetaClass clazz) {
        EClass type = clazz.getType();
        try {
            // call of getEPackageClassName will result in an IllegalStateException when no GenModel was found
            if (type.eIsProxy()) {
                return; // scoping problem; will be reported as separate problem
            }
            genModelHelper.getEPackageClassName(type);
        } catch (IllegalStateException e) {
            error("No genmodel registered for EClass " + type.getName(), clazz, SprayPackage.Literals.META_CLASS__TYPE, IssueCodes.MISSING_GENMODEL);
        }
    }

    @Check
    public void checkDuplicateAliasName(final AliasableElement element) {
        if (element.getAlias() == null)
            return;

        final Predicate<AliasableElement> filter = new Predicate<AliasableElement>() {
            @Override
            public boolean apply(AliasableElement input) {
                return element.getAlias().equals(input.getAlias());
            }
        };

        if (element instanceof MetaClass) {
            Diagram diagram = (Diagram) element.eContainer();
            if (element != Iterables.find(diagram.getMetaClassesList(), filter)) {
                String alias = element.getAlias();
                error("Duplicate alias name " + alias, element, SprayPackage.Literals.ALIASABLE_ELEMENT__ALIAS, IssueCodes.DUPLICATE_ALIAS_NAME, element.getAlias());
            }
        } else {
            MetaClass clazz = EcoreUtil2.getContainerOfType(element, MetaClass.class);
            List<AliasableElement> elements = EcoreUtil2.eAllOfType(clazz, AliasableElement.class);
            elements.remove(clazz);
            // ignore the first element with the alias, but raise errors for all following
            if (element != Iterables.find(elements, filter)) {
                String alias = element.getAlias();
                error("Duplicate alias name " + alias, element, SprayPackage.Literals.ALIASABLE_ELEMENT__ALIAS, IssueCodes.DUPLICATE_ALIAS_NAME, element.getAlias());
            }
        }
    }

    @Check
    public void checkDuplicateConnectionReferences(final MetaClass element) {
        MetaReference found = null;
        for (MetaReference ref : element.getReferences()) {
            found = Iterables.find(Arrays.asList(element.getReferences()), getDuplicateConnectionReferenceFilter(ref));
            if (found != null && found != ref) {
                String referenceName = getReferenceName(ref);
                error("Duplicate connection reference: " + referenceName, element, SprayPackage.Literals.META_CLASS__REFERENCES, IssueCodes.DUPLICATE_CONNECTION_REFERENCE, referenceName);
            }
        }
    }

    private String getReferenceName(MetaReference ref) {
        String name = ref.getTarget().getName();
        if (name == null) {
            name = EcoreUtil2.getURI(ref.getTarget()).toString();
        }
        return name;
    }

    private Predicate<? super MetaReference> getDuplicateConnectionReferenceFilter(final MetaReference reference) {
        return new Predicate<MetaReference>() {
            @Override
            public boolean apply(MetaReference input) {
                return reference.getTarget().equals(input.getTarget());
            }
        };
    }

    @Check
    public void checkConnectionReferenceToContainmentFeature(final MetaClass element) {
        for (MetaReference ref : element.getReferences()) {
            if ((ref.getTarget() != null && ref.getTarget().isContainment())) {
                String referenceName = getReferenceName(ref);
                error("Connection reference to containment reference not supported yet: " + referenceName, element, SprayPackage.Literals.META_CLASS__REFERENCES, IssueCodes.CONTAINMENT_CONNECTION_REFERENCE, referenceName);
            }
        }
    }

    @Check
    public void checkCreateBehavior(final MetaClass element) {
        Predicate<Behavior> createBehaviorFilter = new Predicate<Behavior>() {
            @Override
            public boolean apply(Behavior input) {
                return input instanceof CreateBehavior;
            }
        };
        String name = (element.getType() != null && element.getType().getName() != null) ? element.getType().getName() : element.toString();
        if (!Iterables.filter(element.getBehaviorsList(), createBehaviorFilter).iterator().hasNext()) {
            warning("There is no create behavior defined for class " + name, element, SprayPackage.Literals.META_CLASS__TYPE, IssueCodes.NO_CREATE_BEHAVIOR, name);
        }
    }

    /**
     * The connection to reference must be specified, except for the case of a MetaReference, since there the reference is already defined as target
     * 
     * @param connection
     */
    @Check
    public void checkConnectionFromTo(final ConnectionInSpray connection) {
        if (connection.getTo() == null && !(connection.eContainer() instanceof MetaReference)) {
            error("to reference not specified", SprayPackage.Literals.CONNECTION_IN_SPRAY__TO);
        }
    }

    @Check
    public void checkImports(final Import imp) {
        if (imp.getImportedNamespace().endsWith(".*")) {
            Iterable<EPackage> ePackages = packageSelector.getFilteredEPackages(imp);
            for (EPackage ePackage : ePackages) {
                if ((ePackage.getName() + ".*").equals(imp.getImportedNamespace())) {
                    return;
                }
            }
            error("The import " + imp.getImportedNamespace() + " cannot be resolved", SprayPackage.Literals.IMPORT__IMPORTED_NAMESPACE, IMPORT_NOTEXISTS, new String[0]);
        }
        IJvmTypeProvider typeProvider = typeProviderFactory.findOrCreateTypeProvider(imp.eResource().getResourceSet());
        JvmType jvmType = typeProvider.findTypeByName(imp.getImportedNamespace());
        if (jvmType == null) {
            error("The import " + imp.getImportedNamespace() + " cannot be resolved", SprayPackage.Literals.IMPORT__IMPORTED_NAMESPACE, IMPORT_NOTEXISTS, new String[0]);
        }
    }

    @Check
    public void checkIdReferences(final ShapePropertyAssignment shapePropertyAssignment) {
        ShapeDslKey shapeDslKey = shapePropertyAssignment.getKey();
        if (shapeDslKey != null) {
            String currentKey = shapeDslKey.getDslKey();
            if (currentKey != null) {
                final Predicate<EObject> filterPredicate = textBodyFetcher.getPropertyAssignmentIdsFilter();
                Set<String> textBodyIds = textBodyFetcher.getTextBodyIds(shapePropertyAssignment, textBodyFetcher.getShapeContainerElementResolver(), filterPredicate);
                if (!textBodyIds.contains(currentKey)) {
                    error("The given id " + currentKey + " cannot be resolved inside the referenced shape", SprayPackage.Literals.SHAPE_PROPERTY_ASSIGNMENT__KEY);
                }
            }
        }
    }

    @Check
    public void checkIdReferences(final ShapeCompartmentAssignment shapeCompartmentAssignment) {
        ShapeDslKey shapeDslKey = shapeCompartmentAssignment.getShapeDslKey();
        if (shapeDslKey != null) {
            String currentKey = shapeDslKey.getDslKey();
            if (currentKey != null) {
                final Predicate<EObject> filterPredicate = textBodyFetcher.getCompartmentAssignmentIdsFilter();
                Set<String> textBodyIds = textBodyFetcher.getTextBodyIds(shapeCompartmentAssignment, textBodyFetcher.getShapeContainerElementResolver(), filterPredicate);
                if (!textBodyIds.contains(currentKey)) {
                    error("The given id " + currentKey + " cannot be resolved inside the referenced shape", SprayPackage.Literals.SHAPE_COMPARTMENT_ASSIGNMENT__SHAPE_DSL_KEY);
                }
            }
        }
    }
}
