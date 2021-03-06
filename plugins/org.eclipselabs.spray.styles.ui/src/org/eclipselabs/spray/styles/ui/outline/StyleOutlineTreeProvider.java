/*
 * generated by Xtext
 */
package org.eclipselabs.spray.styles.ui.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipselabs.spray.styles.Gradient;
import org.eclipselabs.spray.styles.Style;
import org.eclipselabs.spray.styles.StyleContainer;

/**
 * customization of the default outline structure
 */
public class StyleOutlineTreeProvider extends DefaultOutlineTreeProvider {
    @Override
    protected void _createChildren(DocumentRootNode parentNode, EObject modelElement) {
        StyleContainer shapeContainer = (StyleContainer) modelElement;

        for (EObject element : shapeContainer.eContents()) {
            createNode(parentNode, element);
        }
    }

    @Override
    protected void _createNode(IOutlineNode parentNode, EObject modelElement) {
        createEObjectNode(parentNode, modelElement);
    }

    protected boolean _isLeaf(Style element) {
        return true;
    }

    protected boolean _isLeaf(Gradient element) {
        return true;
    }

}
