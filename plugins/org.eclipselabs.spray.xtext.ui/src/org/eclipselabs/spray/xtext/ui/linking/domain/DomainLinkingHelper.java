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
package org.eclipselabs.spray.xtext.ui.linking.domain;

import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;
import org.eclipselabs.spray.runtime.xtext.ui.linking.DSLEditorOpener;
import org.eclipselabs.spray.runtime.xtext.ui.linking.DSLLinkingHelper;
import org.eclipselabs.spray.runtime.xtext.ui.linking.DSLResourceVisitor;

import javax.inject.Inject;

public class DomainLinkingHelper extends DSLLinkingHelper<EClassifier> {

    @Inject
    private EObjectAtOffsetHelper eObjectAtOffsetHelper;

    @Inject
    private IJvmModelAssociations modelAssocs;

    @Inject
    private DomainEditorOpener    eObjectEditorOpener;

    @Inject
    private DomainResourceVisitor eObjectResourceVisitor;

    @Override
    protected DSLResourceVisitor<EClassifier> getDSLResourceVisitor(final XtextResource xtextResource) {
        eObjectResourceVisitor.setResourceSet(xtextResource.getResourceSet());
        return eObjectResourceVisitor;
    }

    @Override
    protected boolean isExpectedType(final EObject to) {
        return (to instanceof EClassifier);
    }

    @Override
    protected DSLEditorOpener<EClassifier> getDSLEditorOpener(EClassifier to) {
        eObjectEditorOpener.setEObject(to);
        return eObjectEditorOpener;
    }

    @Override
    protected String getName(EClassifier eObject) {
        return eObject.getName();
    }

    @Override
    protected Set<EObject> getSourceElements(JvmType jvmType) {
        return modelAssocs.getSourceElements(jvmType);
    }

    @Override
    protected EObjectAtOffsetHelper getEObjectAtOffsetHelper() {
        return eObjectAtOffsetHelper;
    }
}
