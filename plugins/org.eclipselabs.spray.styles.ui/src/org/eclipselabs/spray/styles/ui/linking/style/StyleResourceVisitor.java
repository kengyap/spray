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
package org.eclipselabs.spray.styles.ui.linking.style;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider;
import org.eclipse.xtext.common.types.xtext.ui.XtextResourceSetBasedProjectProvider;
import org.eclipselabs.spray.runtime.xtext.ui.linking.DSLResourceVisitor;
import org.eclipselabs.spray.styles.Style;
import org.eclipselabs.spray.styles.StyleContainer;
import org.eclipselabs.spray.styles.StyleContainerElement;

import javax.inject.Inject;

public class StyleResourceVisitor extends DSLResourceVisitor<Style> {
	private static final String STYLE_DSL_FILEEXTENSION = "style";
	
	@Inject
	private IJavaProjectProvider javaProjectProvider;

	@Override
	public void fillFileToEObjects(IResource resource, EObject root,
			Map<IResource, List<Style>> fileToEObjects) {
		List<Style> list;
		if (root instanceof StyleContainer) {
			StyleContainer styleContainer = (StyleContainer) root;
			for (StyleContainerElement ele : styleContainer
					.getStyleContainerElement()) {
				if (ele instanceof Style) {
					list = fileToEObjects.get(resource);
					if (list == null) {
						list = new ArrayList<Style>();
					}
					list.add((Style) ele);
					fileToEObjects.put(resource, list);
				}
			}
		}
	}

	@Override
	protected String getFileExtension() {
		return STYLE_DSL_FILEEXTENSION;
	}

	@Override
	protected IJavaProjectProvider getJavaProjectProvider() {
		if(javaProjectProvider == null) {
			return new XtextResourceSetBasedProjectProvider();
		}
		return javaProjectProvider;
	}
}
