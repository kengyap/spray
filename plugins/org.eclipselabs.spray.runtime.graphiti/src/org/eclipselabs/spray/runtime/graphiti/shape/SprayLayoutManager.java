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
package org.eclipselabs.spray.runtime.graphiti.shape;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipselabs.spray.runtime.graphiti.ISprayConstants;
import org.eclipselabs.spray.runtime.graphiti.layout.ISprayLayoutManager;
import org.eclipselabs.spray.runtime.graphiti.layout.SprayAbstractLayoutManager;
import org.eclipselabs.spray.runtime.graphiti.layout.SprayLayoutService;

public class SprayLayoutManager implements ISprayConstants {

    protected int        minSizeWidth;
    protected int        minSizeHeight;

    protected int        maxSizeWidth;
    protected int        maxSizeHeight;

    protected boolean    stretchVertical;
    protected boolean    stretchHorizontal;

    protected IGaService gaService;

    protected void layout1(Shape targetContainer) {
        if (targetContainer instanceof Diagram) {
            System.out.println("SprayLayoutManager.layout() for Diagram " + SprayLayoutService.getId(targetContainer));
            return;
        }
        while (!SprayLayoutService.isShapeFromDsl(targetContainer) && !(targetContainer.getContainer() instanceof Diagram)) {
            targetContainer = targetContainer.getContainer();
        }
        //        }
        ISprayLayoutManager mgr = SprayLayoutService.getLayoutManager(targetContainer);
        System.out.println("SprayLayoutManager.layout() for " + SprayLayoutService.getId(targetContainer) + " with mgr " + mgr.toString());
        mgr.layout();
    }

    public boolean layout(ILayoutContext context) {
        //        layout1((Shape) context.getPictogramElement());
        layoutOLD(context);
        return true;
    }

    public boolean layoutOLD(ILayoutContext context) {
        boolean anythingChanged = false;
        ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
        GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();

        // Check MinHeight
        if (minSizeHeight > 0 && containerGa.getHeight() < minSizeHeight) {
            containerGa.setHeight(minSizeHeight);
            anythingChanged = true;
        }

        // Check MinWidth
        if (minSizeWidth > 0 && containerGa.getWidth() < minSizeWidth) {
            containerGa.setWidth(minSizeWidth);
            anythingChanged = true;
        }

        // Check MaxHeight
        if (maxSizeHeight > 0 && containerGa.getHeight() > maxSizeHeight) {
            containerGa.setHeight(maxSizeHeight);
            anythingChanged = true;
        }

        // Check MaxHeight
        if (maxSizeWidth > 0 && containerGa.getWidth() > maxSizeWidth) {
            containerGa.setWidth(maxSizeWidth);
            anythingChanged = true;
        }

        gaService = Graphiti.getGaService();

        // Get old size values from element properties
        float oldMaxWidth = SprayLayoutService.getCurrentWidth(containerShape);
        float oldMaxHeight = SprayLayoutService.getCurrentHeight(containerShape);

        // Get new size of container
        float newContainerWidth = containerGa.getWidth();
        float newContainerHeight = containerGa.getHeight();

        // Determine size factors for resizing of elements
        float widthFactor = newContainerWidth / oldMaxWidth;
        float heightFactor = newContainerHeight / oldMaxHeight;

        // Check if something has changed
        if ((widthFactor != 1.0) || (heightFactor != 1.0)) {

            // calculate new element sizes
            resizeElements(containerShape, widthFactor, heightFactor);

            // calculate new anchor positions
            recalculateAnchors(containerShape, widthFactor, heightFactor);

            // set new size to properties of element
            SprayLayoutService.setCurrentHeight(containerShape, (int) Math.round(newContainerHeight));
            SprayLayoutService.setCurrentWidth(containerShape, (int) Math.round(newContainerWidth));

            anythingChanged = true;
        }
        //        fixOffset(containerShape);

        return anythingChanged;
    }

    public void resizeElements(ContainerShape shape, double widthFactor, double heightFactor) {
        System.out.println("SprayLayoutManager.resizeElements() for shape " + SprayLayoutService.getId(shape));
        GraphicsAlgorithm a = shape.getGraphicsAlgorithm();
        // Special layout handling for shapes which only consists of a polyline
        // or polygon
        if (shape.getChildren().size() == 0 && ((a instanceof Polyline) || (a instanceof Polygon))) {
            IDimension size = gaService.calculateSize(a);
            if (a instanceof Polyline) {
                resizePolyline((Polyline) a, size, widthFactor, heightFactor);
            } else if (a instanceof Polygon) {
                resizePolygon((Polygon) a, size, widthFactor, heightFactor);
            }
        } else {
            // calculate new element size recursively
            resizeElementsRecursive(shape, widthFactor, heightFactor);
        }
    }

    public void resizeElementsRecursive(ContainerShape shape, double widthFactor, double heightFactor) {
        System.out.println("SprayLayoutManager.resizeElementsRecursive() for shape " + SprayLayoutService.getId(shape));
        if (shape.getChildren().size() == 0) {
            return;
        } else {
            if (!SprayLayoutService.isCompartment(shape)) {
                for (Shape child : shape.getChildren()) {
                    GraphicsAlgorithm ga = child.getGraphicsAlgorithm();
                    IDimension size = gaService.calculateSize(ga);

                    if (!SprayLayoutService.isShapeFromDsl(child)) {
                        if (ga instanceof Polyline) {
                            resizePolyline((Polyline) ga, size, widthFactor, heightFactor);
                        } else if (ga instanceof Polygon) {
                            resizePolygon((Polygon) ga, size, widthFactor, heightFactor);
                        } else {
                            resizeShape(ga, size, widthFactor, heightFactor);
                        }
                    }

                    if (child instanceof ContainerShape) {
                        if (SprayLayoutService.isShapeFromDsl(child)) {
                            SprayAbstractLayoutManager.debug("Do not resize nested compartments, stopping at " + SprayLayoutService.getId(child));
                        } else {
                            resizeElementsRecursive((ContainerShape) child, widthFactor, heightFactor);
                        }
                    }

                }
            } else {//             SprayLayoutService.isCompartment(shape)
                ISprayLayoutManager mgr = SprayLayoutService.getLayoutManager(shape);
                mgr.layout();
            }
        }
    }

    protected void resizePolyline(Polyline p, IDimension size, double widthFactor, double heightFactor) {
        recalulatePointList(p.getPoints(), widthFactor, heightFactor);
    }

    static public void resizePolygon(Polygon p, IDimension size, double widthFactor, double heightFactor) {
        recalulatePointList(p.getPoints(), widthFactor, heightFactor);
    }

    protected void resizeShape(GraphicsAlgorithm gAlgorithm, IDimension size, double widthFactor, double heightFactor) {
        System.out.println("SprayLayoutManager.resizeShape() for shape " + SprayLayoutService.getId(gAlgorithm.getPictogramElement()));
        // Set new width and height
        int newWidth = (int) Math.round(size.getWidth() * widthFactor);
        int newHeight = (int) Math.round(size.getHeight() * heightFactor);

        // Set new x and y coordinates
        int newXCord = (int) Math.round(gAlgorithm.getX() * widthFactor);
        int newYCord = (int) Math.round(gAlgorithm.getY() * heightFactor);

        gaService.setLocationAndSize(gAlgorithm, newXCord, newYCord, newWidth, newHeight);
    }

    static public void recalulatePointList(EList<Point> points, double widthFactor, double heightFactor) {
        for (Point point : points) {
            int newXCord = (int) Math.round(point.getX() * widthFactor);
            int newYCord = (int) Math.round(point.getY() * heightFactor);

            point.setX(newXCord);
            point.setY(newYCord);
        }
    }

    protected void recalculateAnchors(ContainerShape containerShape, double widthFactor, double heightFactor) {
        for (Anchor anchor : containerShape.getAnchors()) {
            if (anchor instanceof FixPointAnchor) {
                GraphicsAlgorithm ga = anchor.getGraphicsAlgorithm();
                FixPointAnchor fpa = (FixPointAnchor) anchor;

                // set new x and y coordinates for position of anchor
                Point p = fpa.getLocation();
                p.setX((int) Math.round(p.getX() * widthFactor));
                p.setY((int) Math.round(p.getY() * heightFactor));

                // Set new width and height
                ga.setWidth((int) Math.round(ga.getWidth() * widthFactor));
                ga.setHeight((int) Math.round(ga.getHeight() * heightFactor));

                // Set new x and y coordinates
                ga.setX((int) Math.round(ga.getX() * widthFactor));
                ga.setY((int) Math.round(ga.getY() * heightFactor));
            }
        }
    }

    public int getMinSizeWidth() {
        return minSizeWidth;
    }

    public void setMinSizeWidth(int minSizeWidth) {
        this.minSizeWidth = minSizeWidth;
    }

    public int getMinSizeHeight() {
        return minSizeHeight;
    }

    public void setMinSizeHeight(int minSizeHeight) {
        this.minSizeHeight = minSizeHeight;
    }

    public int getMaxSizeWidth() {
        return maxSizeWidth;
    }

    public void setMaxSizeWidth(int maxSizeWidth) {
        this.maxSizeWidth = maxSizeWidth;
    }

    public int getMaxSizeHeight() {
        return maxSizeHeight;
    }

    public void setMaxSizeHeight(int maxSizeHeight) {
        this.maxSizeHeight = maxSizeHeight;
    }

    public static void setSizePictogramProperties(PictogramElement pe) {
        GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
        int maxWidth = 0;
        int maxHeight = 0;

        if ((ga instanceof Polyline) || (ga instanceof Polygon)) {
            Polyline element = (Polyline) ga;
            EList<Point> points = element.getPoints();

            for (Point point : points) {
                if (point.getX() > maxWidth) {
                    maxWidth = point.getX();
                }
                if (point.getY() > maxHeight) {
                    maxHeight = point.getY();
                }
            }
        } else {
            maxHeight = ga.getHeight();
            maxWidth = ga.getWidth();
        }
        SprayLayoutService.setCurrentHeight(pe, maxHeight);
        SprayLayoutService.setCurrentWidth(pe, maxWidth);
    }

    public boolean isStretchVertical() {
        return stretchVertical;
    }

    public void setStretchVertical(boolean stretchVertical) {
        this.stretchVertical = stretchVertical;
    }

    public boolean isStretchHorizontal() {
        return stretchHorizontal;
    }

    public void setStretchHorizontal(boolean stretchHorizontal) {
        this.stretchHorizontal = stretchHorizontal;
    }

}
