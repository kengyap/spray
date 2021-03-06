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
package org.eclipselabs.spray.generator.graphiti.templates.features

import javax.inject.Inject
import org.eclipse.emf.ecore.EClass
import org.eclipselabs.spray.generator.graphiti.util.NamingExtensions
import org.eclipselabs.spray.generator.graphiti.util.mm.MetaReferenceExtensions
import org.eclipselabs.spray.mm.spray.MetaReference
import org.eclipselabs.spray.xtext.generator.FileGenerator

import static org.eclipselabs.spray.generator.common.GeneratorUtil.*


class UpdateReferenceAsListFeature extends FileGenerator<MetaReference> {
    @Inject extension NamingExtensions
    @Inject extension MetaReferenceExtensions
    
    EClass target 
    
    def setTarget(EClass m){
        target = m 
    }
    
    override CharSequence generateBaseFile(MetaReference modelElement) {
        mainFile( modelElement, javaGenFile.baseClassName)
    }

    override CharSequence generateExtensionFile(MetaReference modelElement) {
        mainExtensionPointFile( modelElement, javaGenFile.className)
    }
    
    def mainExtensionPointFile(MetaReference metaReference, String className) '''    
        «extensionHeader(this)»
        package «feature_package()»;
        
        import org.eclipse.graphiti.features.IFeatureProvider;
        
        public class «className» extends «className»Base {
            public «className»(final IFeatureProvider fp) {
                super(fp);
            }
        
        }
    '''

    def mainFile(MetaReference reference, String className) '''
        «header(this)»
        package «feature_package()»;

        import org.eclipse.emf.ecore.EObject;
        import org.eclipse.graphiti.features.IFeatureProvider;
        import org.eclipse.graphiti.features.IReason;
        import org.eclipse.graphiti.features.context.IUpdateContext;
        import org.eclipse.graphiti.features.impl.Reason;
        import org.eclipse.graphiti.mm.algorithms.AbstractText;
        import org.eclipse.graphiti.mm.pictograms.PictogramElement;
        import org.eclipse.graphiti.mm.pictograms.Shape;
        import org.eclipse.graphiti.services.IGaService;
        import org.eclipselabs.spray.runtime.graphiti.features.AbstractUpdateFeature;
        // MARKER_IMPORT
        
        public abstract class «className» extends AbstractUpdateFeature {
            «generate_additionalFields(reference)»
            public «className»(final IFeatureProvider fp) {
                super(fp);
                gaService = «reference.diagram.activatorClassName.shortName».get(IGaService.class);
            }
            
            «generate_canUpdate(reference)»
            «generate_updateNeeded(reference)»
            «generate_update(reference)»
            «generate_getText(reference)»
            «generate_additionalMethods(reference)»
        }
    '''
    
    def generate_canUpdate (MetaReference reference) '''
        «overrideHeader»
        public boolean canUpdate(final IUpdateContext context) {
            // return true, if linked business object is a EClass
            final EObject bo =  getBusinessObjectForPictogramElement(context.getPictogramElement());
            return (bo instanceof «target.itfName»);
        }
    '''
    
    def generate_updateNeeded (MetaReference reference) '''
        «overrideHeader»
        public IReason updateNeeded(final IUpdateContext context) {
            final PictogramElement pictogramElement = context.getPictogramElement();
            final EObject bo = getBusinessObjectForPictogramElement(pictogramElement);
            // retrieve name from pictogram model
            String pictogramName = null;
            if (pictogramElement instanceof Shape) {
                final Shape cs = (Shape) pictogramElement;
                if (cs.getGraphicsAlgorithm() instanceof AbstractText) {
                    final AbstractText text = (AbstractText) cs.getGraphicsAlgorithm();
                    // peService.getPropertyValue(shape, "REFERENCE");
                    if( pictogramName == null ){
                        pictogramName = text.getValue();
                    }
                }
            }
     
            // retrieve name from business model
            String businessName = null;
            if (bo instanceof «target.itfName») {
                final «target.itfName» «target.name.toFirstLower» = («target.itfName») bo;
                businessName = getText(context, «target.name.toFirstLower»);
            }
     
            // update needed, if names are different
            boolean updateNameNeeded =
                ((pictogramName == null && businessName != null) ||
                    (pictogramName != null && !pictogramName.equals(businessName)));
            if (updateNameNeeded) {
                return Reason.createTrueReason("Property Name is out of date");
            } else {
                return Reason.createFalseReason();
            }
          }
    '''
    
    def generate_update (MetaReference reference) '''
        «overrideHeader»
        public boolean update(IUpdateContext context) {
            final PictogramElement pictogramElement = context.getPictogramElement();
            final EObject bo = getBusinessObjectForPictogramElement(pictogramElement);
           // retrieve name from business model
            String businessName = null;
            if (bo instanceof «target.itfName») {
                final «target.itfName» «target.name.toFirstLower» = («target.itfName») bo;
                businessName = getText(context, «target.name.toFirstLower»);
            }

            // Set name in pictogram model
            if (pictogramElement instanceof Shape) {
                final Shape cs = (Shape) pictogramElement;
                if (cs.getGraphicsAlgorithm() instanceof AbstractText) {
                    final AbstractText text = (AbstractText) cs.getGraphicsAlgorithm();
                    text.setValue(businessName);
                    layoutPictogramElement(cs.getContainer().getContainer());
                    setDoneChanges(true);
                    return true;
                }
            }
            return false;
        }
    '''
    
    def generate_getText (MetaReference reference) '''
        /**
         * Computes the displayed text. Clients may override this method.
         */
        protected String getText (final IUpdateContext context, final «target.itfName» bo) {
            return bo.getName();
        }
    '''
}