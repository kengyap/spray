package org.eclipselabs.spray.runtime.graphiti.styles;

import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public interface ISprayStyle {
	public Style newStyle(Diagram diagram);
	public Style getStyle(Diagram diagram);
	public Color getFontColor(Diagram diagram);
	public AdaptedGradientColoredAreas getColorSchema();
}