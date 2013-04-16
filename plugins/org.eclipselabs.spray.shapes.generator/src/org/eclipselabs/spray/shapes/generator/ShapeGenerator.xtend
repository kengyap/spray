/*
 * generated by Xtext
 */
package org.eclipselabs.spray.shapes.generator

import com.google.inject.Inject
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractFileSystemAccess
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.OutputConfiguration
import org.eclipselabs.spray.shapes.ConnectionDefinition
import org.eclipselabs.spray.shapes.ShapeContainerElement
import org.eclipselabs.spray.shapes.ShapeDefinition

import static org.eclipselabs.spray.shapes.generator.ImageConstants.*
import static org.eclipselabs.spray.shapes.generator.ShapeGenerator.*
import org.eclipselabs.spray.generator.common.ProjectProperties
import org.eclipselabs.spray.shapes.generator.shapes.ShapeDefinitionGenerator
import org.eclipselabs.spray.xtext.generator.filesystem.JavaGenFile
import com.google.inject.Provider
import org.eclipselabs.spray.shapes.generator.shapes.ConnectionDefinitionGenerator

class ShapeGenerator implements IGenerator {
    @Inject Provider<JavaGenFile> genFileProvider
	@Inject ShapeDefinitionGenerator shapeDefinition
    @Inject ConnectionDefinitionGenerator connectionDefinition 
    @Inject GeneratorSVGDefinition svgDefinition 
    private static Log   LOGGER       = LogFactory::getLog("ShapeGenerator");
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
        if (!resource.URI.lastSegment().endsWith(ProjectProperties::SHAPES_FILE_EXTENSION)) {
            LOGGER.info("Shape generator is NOT producing Graphiti code for model " + resource.URI)
            return;
        }
        LOGGER.info("Shape generator is producing Graphiti code for model " + resource.URI)
        ProjectProperties::setModelUri(resource.URI)
        if( ! resource.loaded ){
            resource.load(null);
        }
        
        val JavaGenFile java = genFileProvider.get()
        java.access = fsa
        
   		val svgOutputConfName = "svgOutputConf"
   		fsa.addSVGOutputConfiguration(svgOutputConfName)
        for(shapeContainerElement : resource.allContents.toIterable.filter(typeof(ShapeContainerElement))) {
            java.generateJava(shapeContainerElement)
        }
        for(shapeContainerElement : resource.allContents.toIterable.filter(typeof(ShapeContainerElement))) {
            val svgContent = svgDefinition.compile(shapeContainerElement)
            fsa.generateFile(svgDefinition.filepath(shapeContainerElement), svgOutputConfName, svgContent)
        }
	}
	
	def dispatch generateJava(JavaGenFile java, ShapeDefinition shape) {
		java.hasExtensionPoint = true
		shapeDefinition.generate(shape, java)
	}
	
	def dispatch generateJava(JavaGenFile java, ConnectionDefinition connection) {
		java.hasExtensionPoint = true
		connectionDefinition.generate(connection, java)
	}	
	
	def private addSVGOutputConfiguration(IFileSystemAccess fsa, String svgOutputConfName) {
   		fsa.addImageOutputConfiguration(svgOutputConfName, SVG_PATH)
	}	
	
	def private addImageOutputConfiguration(IFileSystemAccess fsa, String outputConfName, String path) {
   		if(fsa instanceof AbstractFileSystemAccess) {
   			val aFsa = fsa as AbstractFileSystemAccess
   			if(!aFsa.outputConfigurations.containsKey(outputConfName)) {
	   			val outputConfigurations = <String, OutputConfiguration> newHashMap
	   			outputConfigurations.putAll(aFsa.outputConfigurations)
	   			val imageOutputConfiguration = new OutputConfiguration(outputConfName)
	   			imageOutputConfiguration.outputDirectory = path
	   			imageOutputConfiguration.createOutputDirectory = true
	   			imageOutputConfiguration.overrideExistingResources = true
	   			imageOutputConfiguration.setDerivedProperty = true
	   			outputConfigurations.put(outputConfName, imageOutputConfiguration) 
	   			aFsa.setOutputConfigurations(outputConfigurations)
   			}
   		}
	}	
}
