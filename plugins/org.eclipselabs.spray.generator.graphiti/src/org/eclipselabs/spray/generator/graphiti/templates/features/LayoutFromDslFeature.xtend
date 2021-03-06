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
import org.eclipselabs.spray.generator.common.ProjectProperties
import org.eclipselabs.spray.generator.graphiti.util.NamingExtensions
import org.eclipselabs.spray.mm.spray.ShapeFromDsl
import org.eclipselabs.spray.xtext.generator.FileGenerator

import static org.eclipselabs.spray.generator.common.GeneratorUtil.*

class LayoutFromDslFeature extends FileGenerator<ShapeFromDsl> {
    @Inject extension NamingExtensions
    
    override CharSequence generateBaseFile(ShapeFromDsl modelElement) {
        mainFile( modelElement, javaGenFile.baseClassName)
    }

    override CharSequence generateExtensionFile(ShapeFromDsl modelElement) {
        mainExtensionPointFile( modelElement, javaGenFile.className)
    }
    
    def mainExtensionPointFile(ShapeFromDsl container, String className) '''
        «extensionHeader(this)»
        package «feature_package()»;
        
        import org.eclipse.graphiti.features.IFeatureProvider;
        
        public class «className» extends «className»Base {
            public «className»(final IFeatureProvider fp) {
                super(fp);
            }
        
        }
    '''

    def mainFile(ShapeFromDsl container, String className) '''
        «header(this)»
        package «feature_package()»;
        
        import org.eclipse.emf.common.util.EList;
        import org.eclipse.emf.ecore.EObject;
        import org.eclipse.graphiti.features.IFeatureProvider;
        import org.eclipse.graphiti.features.context.ILayoutContext;
        import org.eclipse.graphiti.mm.pictograms.ContainerShape;
        import org.eclipse.graphiti.mm.pictograms.PictogramElement;
        import org.eclipselabs.spray.runtime.graphiti.features.AbstractLayoutFeature;
        import org.eclipselabs.spray.runtime.graphiti.shape.SprayLayoutManager;
        import «ProjectProperties::shapesPackage».«container.shape.simpleName»Shape;
        
        // MARKER_IMPORT
        
        public abstract class «className» extends AbstractLayoutFeature {
        	
        	SprayLayoutManager layoutManager; 
            «generate_additionalFields(container)»
        
            public «className»(final IFeatureProvider fp) {
                super(fp);
                layoutManager =  new «container.shape.simpleName»Shape(fp).getShapeLayout( );
            }
         
            «generate_canLayout(container)»
            «generate_layout(container)»
            «generate_additionalMethods(container)»
        }
        '''
        
        def generate_canLayout (ShapeFromDsl container) '''
            «overrideHeader»
            public boolean canLayout(final ILayoutContext context) {
               final PictogramElement pe = context.getPictogramElement();
               if (!(pe instanceof ContainerShape)) {
                   return false;
               }
               final EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
               return (businessObjects.size() == 1) && (businessObjects.get(0) instanceof «container.represents.type.itfName»);
            }
        '''
        
        def generate_layout (ShapeFromDsl container) '''
            «overrideHeader»
            public boolean layout(final ILayoutContext context) {
            	return layoutManager.layout(context);
            }
        '''
}