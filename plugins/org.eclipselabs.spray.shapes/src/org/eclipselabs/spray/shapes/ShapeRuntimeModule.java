/*
 * generated by Xtext
 */
package org.eclipselabs.spray.shapes;

import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipselabs.spray.shapes.scoping.ShapeScopeProvider;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class ShapeRuntimeModule extends org.eclipselabs.spray.shapes.AbstractShapeRuntimeModule {
	@Override
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return ShapeScopeProvider.class;
	}

	//@Override
	//public Class<? extends org.eclipse.xtext.generator.IGenerator> bindIGenerator() {
		//return org.eclipselabs.spray.shapes.generator.ShapeGenerator.class;
	//}

	// contributed by
	// org.eclipse.xtext.generator.scoping.AbstractScopingFragment
	public void configureIScopeProviderDelegate(com.google.inject.Binder binder) {
		binder.bind(org.eclipse.xtext.scoping.IScopeProvider.class)
				.annotatedWith(com.google.inject.name.Names.named(org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider.NAMED_DELEGATE))
				.to(org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider.class);
	}

	// contributed by
	// org.eclipse.xtext.generator.exporting.QualifiedNamesFragment
	public Class<? extends org.eclipse.xtext.naming.IQualifiedNameProvider> bindIQualifiedNameProvider() {
		return org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider.class;
	}


}
