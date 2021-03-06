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
package org.eclipselabs.spray.shapes.ui.linking.connection;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;
import org.eclipselabs.spray.runtime.xtext.ui.linking.DSLEditorOpener;
import org.eclipselabs.spray.runtime.xtext.ui.linking.DSLLinkingHelper;
import org.eclipselabs.spray.runtime.xtext.ui.linking.DSLResourceVisitor;
import org.eclipselabs.spray.shapes.ConnectionDefinition;

import javax.inject.Inject;

public class ConnectionLinkingHelper extends DSLLinkingHelper<ConnectionDefinition> {

    @Inject
    private EObjectAtOffsetHelper     eObjectAtOffsetHelper;

    @Inject
    private IJvmModelAssociations     modelAssocs;

    @Inject
    private ConnectionEditorOpener    shapeEditorOpener;

    @Inject
    private ConnectionResourceVisitor shapeResourceVisitor;

    @Override
    protected DSLResourceVisitor<ConnectionDefinition> getDSLResourceVisitor(final XtextResource xtextResource) {
        shapeResourceVisitor.setResourceSet(xtextResource.getResourceSet());
        return shapeResourceVisitor;
    }

    @Override
    protected boolean isExpectedType(final EObject to) {
        return to instanceof ConnectionDefinition;
    }

    @Override
    protected DSLEditorOpener<ConnectionDefinition> getDSLEditorOpener(ConnectionDefinition to) {
        shapeEditorOpener.setEObject(to);
        return shapeEditorOpener;
    }

    @Override
    protected String getName(ConnectionDefinition shape) {
        return shape.getName();
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
