/*
 * generated by Xtext
 */
package org.eclipselabs.spray.styles.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator

import static extension org.eclipse.xtext.xbase.lib.IteratorExtensions.*

import org.eclipselabs.spray.styles.styles.Style
import org.eclipselabs.spray.styles.styles.StyleLayout
import org.eclipselabs.spray.styles.styles.Transparent
import org.eclipselabs.spray.styles.styles.ColorConstantRef
import org.eclipselabs.spray.styles.styles.RGBColor
import org.eclipselabs.spray.styles.styles.ColorWithTransparency
import org.eclipselabs.spray.styles.styles.YesNoBool
import org.eclipselabs.spray.styles.styles.LineStyle

class StyleGenerator implements IGenerator {
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		for(style : resource.allContents.toIterable.filter(typeof(Style))) {
      		fsa.generateFile(style.filepath, style.compile)
   		}
	}
	
	def filepath(Style s) { s.packagePath + s.className + ".java" }
	def className(Style s) { s.name.toFirstUpper }
	def packageName(Style s) { "org.eclipselabs.spray.styles" }
	def packagePath(Style s) { "org/eclipselabs/spray/styles/" }
	
	def compile(Style s) {
		'''
		«s.head»
		
		«s.body»
		'''
	}
	
	def head(Style s) {
		'''
		/**
		 * This is a generated Style class for Spray.
		 */
		package «s.packageName»;
		
		import org.eclipse.graphiti.mm.pictograms.Diagram;
		import org.eclipse.graphiti.mm.algorithms.styles.Style;
		import org.eclipse.graphiti.mm.algorithms.styles.Color;
		import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
		import org.eclipse.graphiti.services.Graphiti;
		import org.eclipse.graphiti.services.IGaService;
		import org.eclipse.graphiti.util.ColorConstant;
		import org.eclipse.graphiti.util.IColorConstant;
«««		import org.eclipselabs.spray.styles.ISprayStyle;
		«IF s.superStyle == null»
«««		import org.eclipselabs.spray.styles.DefaultSprayStyle;
		«ELSE»
		import «s.superStyle.qualifiedName»;
		«ENDIF»
		'''
	}
	
	def body(Style s) {
		'''
		/**
		 * This is a generated Style class for Spray.
		 * Description: «s.description»
		 */
		@SuppressWarnings("all")
		public class «s.className» extends «s.createSuperStyle» implements ISprayStyle {
		    
		    /**
			 * This method creates a Style and returns the defined style.
			 * Description: «s.description»
			 */
		    @Override
			public Style getStyle(Diagram diagram) {
				IGaService gaService = Graphiti.getGaService();
				
				// Creating Style with given id and description
				Style style = super.getStyle(diagram);
				style.setId("«s.name»");
				style.setDescription("«s.description»");
				
				«s.layout.createLayout»
			    
				return style;
			}
			
		    /**
			 * This method returns the font color for the style. 
			 * The font color will be returned separated, because Graphiti allows just the foreground color.
			 * The foreground color will be used for lines and fonts at the same time.
			 */
			@Override
			public Color getFontColor(Diagram diagram) {
				«s.layout.createFontColor»
			}
			
		}	
		'''
	}

	def createSuperStyle(Style s) {
		if(s.superStyle == null) "DefaultSprayStyle" else s.superStyle.simpleName
	}

	def getStyle(Style s) {
		if(s.superStyle == null)
			'''gaService.createStyle(diagram, "«s.name»");'''
		else 
			'''super.getStyle(diagram);'''			
	}

    def createLayout(StyleLayout l) {
        '''
        «l.createTransparencyAttributes»

        «l.createBackgroundAttributes»
        
        «l.createLineAttributes»

        «l.createFontAttributes»
        '''
    }

    def createTransparencyAttributes(StyleLayout l) {
        '''
        // transparency value
        «IF !(l == null || l.transparency == Double::MIN_VALUE)»
        style.setTransparency(«l.transparency»);
        «ENDIF»
        '''    
    }
        
    def createBackgroundAttributes(StyleLayout l) {
        '''
        // background attributes
        «IF l == null || l.background == null»
        «ELSEIF l.background instanceof Transparent»
        style.setFilled(false);
        «ELSE»
        style.setFilled(true);
        style.setBackground(gaService.manageColor(diagram, «l.background.createColorValue»));
        «ENDIF»
        '''    
    }
    
    def createLineAttributes(StyleLayout l) {
        '''
        // line attributes
        «IF l == null || l.lineColor == null»
        «ELSEIF l.lineColor instanceof Transparent»
        style.setLineVisible(false);
        «ELSE»
		style.setLineVisible(true);
		style.setForeground(gaService.manageColor(diagram, «l.lineColor.createColorValue»));
		«IF l.lineWidth > 0»
		style.setLineWidth(«Math::max(l.lineWidth,1)»);
		«ENDIF»
        «IF l.lineStyle != LineStyle::NULL» 
		style.setLineStyle(LineStyle.«l.lineStyle.name»);
        «ENDIF»
        «ENDIF»
        '''    
    }

    def createFontAttributes(StyleLayout l) {
        '''
		// font attributes
        «IF l == null || l.fontName == null»
		String fontName = style.getFont().getName();
        «ELSE»
		String fontName = "«l.fontName»";
        «ENDIF»
        «IF l == null || l.fontSize == Integer::MIN_VALUE»
		int fontSize = style.getFont().getSize();
        «ELSE»
		int fontSize = «l.fontSize»;
 	    «ENDIF»
 	    «IF l == null || l.fontItalic == YesNoBool::NULL»
		boolean fontItalic = style.getFont().isItalic();
 	    «ELSE»
		boolean fontItalic = «l.fontItalic.transformYesNoToBoolean»;
 	    «ENDIF»
		«IF l == null || l.fontBold == YesNoBool::NULL»
		boolean fontBold = style.getFont().isBold();
		«ELSE»
		boolean fontBold = «l.fontBold.transformYesNoToBoolean»;
		«ENDIF»
		style.setFont(gaService.manageFont(diagram, fontName, fontSize, fontItalic, fontBold));
        '''    
    }
    
    def createFontColor(StyleLayout l) {
    	if(l == null || l.fontColor == null) {
 			if(l == null || l.lineColor == null) {
				'''return super.getFontColor(diagram);'''
 			} else {
 				l.lineColor.createFontColor
 			}
    	} else {
    		l.fontColor.createFontColor
    	}
    }
    
    def createFontColor(ColorWithTransparency c) {
    	'''
		IGaService gaService = Graphiti.getGaService();
		return gaService.manageColor(diagram, «c.createColorValue»);
    	'''
    }
    
    def transformYesNoToBoolean(YesNoBool yesNo) { if(yesNo == YesNoBool::YES) "true" else "false" }
    def dispatch createColorValue(Transparent c) { '''null''' }
    def dispatch createColorValue(ColorConstantRef c) { '''IColorConstant.«c.value.name»''' }
	def dispatch createColorValue(RGBColor c) { '''new ColorConstant(«c.red», «c.green», «c.blue»)''' }
	
}
