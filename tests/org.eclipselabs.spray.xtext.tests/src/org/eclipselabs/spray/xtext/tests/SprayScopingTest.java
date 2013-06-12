package org.eclipselabs.spray.xtext.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.InjectParameter;
import org.eclipse.xtext.junit4.parameterized.Offset;
import org.eclipse.xtext.junit4.parameterized.ParameterSyntax;
import org.eclipse.xtext.junit4.parameterized.ParameterizedXtextRunner;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.junit4.parameterized.XpectCommaSeparatedValues;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.util.Pair;
import org.eclipselabs.spray.shapes.scoping.ShapeScopeProvider;
import org.eclipselabs.spray.xtext.SprayTestsInjectorProvider;
import org.junit.runner.RunWith;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

@RunWith(ParameterizedXtextRunner.class)
@InjectWith(SprayTestsInjectorProvider.class)
@ResourceURIs(baseDir="model/testcases/scoping", fileExtensions={"spray"})
public class SprayScopingTest {

	@InjectParameter
	private Offset offset;
	
	@Inject
	private ShapeScopeProvider shapeScopeProvider;

	@ParameterSyntax("('at' offset=OFFSET)?")
	@XpectCommaSeparatedValues
	public Iterable<String> elementsInScope() {
		Pair<EObject, EStructuralFeature> pair = offset.getEStructuralFeatureByParent();
		
		IScope scope = shapeScopeProvider.getScope(pair.getFirst(), (EReference) pair.getSecond());
		
		Function<IEObjectDescription, String> transformer = new Function<IEObjectDescription, String>() {
			
			public String apply(IEObjectDescription eoDesc) {
				return eoDesc.getName().toString();
			}
		};
		
		return Iterables.transform(scope.getAllElements(), transformer);
	}
}