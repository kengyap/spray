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
package org.eclipselabs.spray.shapes.generator.shapes

import org.eclipselabs.spray.shapes.ShapeDefinition

class ShapeLayoutGenerator {
	
	def generateLayout(ShapeDefinition s) {
		'''
		«IF s.shapeLayout.proportional != null»
		sprayStyle.getStyle(diagram).setProportional(«s.shapeLayout.proportional»);
		«ELSE»
		sprayStyle.getStyle(diagram).setProportional(false);
		«ENDIF»		
		«IF s.shapeLayout.stretchH != null»
		sprayStyle.getStyle(diagram).setStretchH(«s.shapeLayout.stretchH»);
		SprayLayoutService.getLayoutData(containerShape).setHorizontalStrechable(«s.shapeLayout.stretchH»);
		«ELSE»
		sprayStyle.getStyle(diagram).setStretchH(false);
		«ENDIF»		
		«IF s.shapeLayout.stretchV != null»
		sprayStyle.getStyle(diagram).setStretchV(«s.shapeLayout.stretchV»);
		SprayLayoutService.getLayoutData(containerShape).setVerticalStrechable(«s.shapeLayout.stretchV»);
		«ELSE»
		sprayStyle.getStyle(diagram).setStretchV(false);
		«ENDIF»		
		'''
	}
	
		def generateGetLayoutMethod(ShapeDefinition s) {
		'''
		public SprayLayoutManager getShapeLayout() {
		    SprayLayoutManager layoutManager = new SprayLayoutManager();
		    «IF s.shapeLayout.minwidth != 0»
		      layoutManager.setMinSizeWidth(«s.shapeLayout.minwidth»);
		    «ELSE»
		      layoutManager.setMinSizeWidth(-1);
		    «ENDIF»
		    «IF s.shapeLayout.maxwidth != 0»
		      layoutManager.setMaxSizeWidth(«s.shapeLayout.maxwidth»);
		    «ELSE»
		      layoutManager.setMaxSizeWidth(-1);
		    «ENDIF»
		    «IF s.shapeLayout.minheight != 0»
		      layoutManager.setMinSizeHeight(«s.shapeLayout.minheight»);
		    «ELSE»
		      layoutManager.setMinSizeHeight(-1);
		    «ENDIF»	
		    «IF s.shapeLayout.maxheight != 0»
		      layoutManager.setMaxSizeHeight(«s.shapeLayout.maxheight»);
		    «ELSE»
		      layoutManager.setMaxSizeHeight(-1);
		    «ENDIF»
		    «IF s.shapeLayout.stretchH != null»
		      layoutManager.setStretchHorizontal(«s.shapeLayout.stretchH»);
		    «ELSE»
		      layoutManager.setStretchHorizontal(true);
		    «ENDIF»
		    «IF s.shapeLayout.stretchV != null»
		      layoutManager.setStretchVertical(«s.shapeLayout.stretchV»);
		    «ELSE»
		      layoutManager.setStretchVertical(true);
		    «ENDIF»
		    return layoutManager;
		}
		'''
	}
	
}