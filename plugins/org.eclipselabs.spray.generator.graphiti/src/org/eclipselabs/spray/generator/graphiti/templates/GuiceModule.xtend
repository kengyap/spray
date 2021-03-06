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
package org.eclipselabs.spray.generator.graphiti.templates

import org.eclipselabs.spray.mm.spray.Diagram
import org.eclipselabs.spray.xtext.generator.FileGenerator

class GuiceModule extends FileGenerator<Diagram> {
    
    override CharSequence generateBaseFile(Diagram modelElement) {
        mainFile( modelElement, javaGenFile.baseClassName)
    }

    override CharSequence generateExtensionFile(Diagram modelElement) {
        mainExtensionPointFile( modelElement, javaGenFile.className)
    }

    def mainExtensionPointFile(Diagram diagram, String className) '''
        «extensionHeader(this)»
        package «javaGenFile.packageName»;
        
        public class «className» extends «className»Base {
             // Add custom bindings here
             // public Class<? extends MyInterface> bindMyInterface () {
             //   return MyInterfaceImpl.class;
             // }
             //
             // Get instances through the Activator:
             // MyInterface instance = Activator.get(MyInterface.class);
        }
    '''
    
    def mainFile(Diagram diagram, String className) '''
        «header(this)»
        package «javaGenFile.packageName»;

        import org.eclipse.core.resources.IWorkspace;
        import org.eclipse.core.resources.ResourcesPlugin;
        import org.eclipse.ui.IWorkbench;
        import org.eclipse.ui.PlatformUI;
        import org.eclipse.xtext.service.AbstractGenericModule;
        import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;
        import org.eclipse.xtext.ui.editor.IURIEditorOpener;
        import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
        import org.eclipse.xtext.ui.resource.Storage2UriMapperImpl;
        
        import com.google.inject.Binder;
        import com.google.inject.name.Names;

        public class «className» extends AbstractGenericModule {
            public Class<? extends IURIEditorOpener> bindIURIEditorOpener() {
                return GlobalURIEditorOpener.class;
            }

            @Override
            public void configure(Binder binder) {
                binder.bind(IStorage2UriMapper.class).to(Storage2UriMapperImpl.class);
                binder.bind(IWorkbench.class).toInstance(PlatformUI.getWorkbench());
                binder.bind(IWorkspace.class).toInstance(ResourcesPlugin.getWorkspace());
                binder.bind(String.class).annotatedWith(Names.named("diagramTypeId")).toInstance("«diagram.name»");
            }
        }
   '''
}