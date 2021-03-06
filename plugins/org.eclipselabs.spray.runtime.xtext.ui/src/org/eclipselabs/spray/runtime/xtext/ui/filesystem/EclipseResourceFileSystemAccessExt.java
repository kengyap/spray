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
package org.eclipselabs.spray.runtime.xtext.ui.filesystem;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipselabs.spray.xtext.generator.IPostProcessor;

import javax.inject.Inject;
import com.google.inject.name.Named;

public class EclipseResourceFileSystemAccessExt extends EclipseResourceFileSystemAccess2 implements IAdaptable {
    @Inject
    @Named("java")
    private IPostProcessor javaPostProcessor;
    private IProject       project;

    @Override
    public void generateFile(String fileName, String outputName, CharSequence contents) {
        if (javaPostProcessor.accepts(fileName)) {
            contents = javaPostProcessor.process(fileName, contents);
        }
        super.generateFile(fileName, outputName, contents);
    }

    @Override
    public void setProject(IProject project) {
        super.setProject(project);
        this.project = project;
    }

    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
        if (IProject.class == adapter) {
            return project;
        }
        return null;
    }
}
