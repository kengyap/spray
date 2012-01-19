/*
 * generated by Xtext
 */
package org.eclipselabs.spray.shapes.generator

import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipselabs.spray.shapes.shapes.ConnectionDefinition
import org.eclipselabs.spray.shapes.shapes.ShapeDefinition

class ShapeGenerator implements IGenerator {

	@Inject extension GeneratorShapeDefinition shapeGenerator
	@Inject extension GeneratorConnectionDefinition connectionGenerator 
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		for(shape : resource.allContents.toIterable.filter(typeof(ShapeDefinition))) {
    		// create the Shapes
	  		fsa.generateFile(shape.filepath, shape.compile)
   		}
   		for(connection : resource.allContents.toIterable.filter(typeof(ConnectionDefinition))) {
      		// create the connections
   			fsa.generateFile(connection.filepath, connection.compile)
   		}
	}
	
}
