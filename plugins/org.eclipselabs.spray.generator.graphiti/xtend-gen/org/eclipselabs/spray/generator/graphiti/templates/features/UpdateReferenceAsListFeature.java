package org.eclipselabs.spray.generator.graphiti.templates.features;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.eclipselabs.spray.generator.graphiti.templates.FileGenerator;
import org.eclipselabs.spray.generator.graphiti.templates.JavaGenFile;
import org.eclipselabs.spray.generator.graphiti.util.GeneratorUtil;
import org.eclipselabs.spray.generator.graphiti.util.NamingExtensions;
import org.eclipselabs.spray.generator.graphiti.util.mm.MetaReferenceExtensions;
import org.eclipselabs.spray.mm.spray.Diagram;
import org.eclipselabs.spray.mm.spray.MetaReference;

@SuppressWarnings("all")
public class UpdateReferenceAsListFeature extends FileGenerator {
  @Inject
  private NamingExtensions _namingExtensions;
  
  @Inject
  private MetaReferenceExtensions _metaReferenceExtensions;
  
  private EClass target;
  
  public EClass setTarget(final EClass m) {
    EClass _target = this.target = m;
    return _target;
  }
  
  public StringConcatenation generateBaseFile(final EObject modelElement) {
    JavaGenFile _javaGenFile = this.getJavaGenFile();
    String _baseClassName = _javaGenFile.getBaseClassName();
    StringConcatenation _mainFile = this.mainFile(((MetaReference) modelElement), _baseClassName);
    return _mainFile;
  }
  
  public StringConcatenation generateExtensionFile(final EObject modelElement) {
    JavaGenFile _javaGenFile = this.getJavaGenFile();
    String _className = _javaGenFile.getClassName();
    StringConcatenation _mainExtensionPointFile = this.mainExtensionPointFile(((MetaReference) modelElement), _className);
    return _mainExtensionPointFile;
  }
  
  public StringConcatenation mainExtensionPointFile(final MetaReference metaReference, final String className) {
    StringConcatenation _builder = new StringConcatenation();
    StringConcatenation _extensionHeader = this.extensionHeader(this);
    _builder.append(_extensionHeader, "");
    _builder.newLineIfNotEmpty();
    _builder.append("package ");
    String _feature_package = GeneratorUtil.feature_package();
    _builder.append(_feature_package, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.IFeatureProvider;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(className, "");
    _builder.append(" extends ");
    _builder.append(className, "");
    _builder.append("Base {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("public ");
    _builder.append(className, "    ");
    _builder.append("(IFeatureProvider fp) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("super(fp);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public StringConcatenation mainFile(final MetaReference reference, final String className) {
    StringConcatenation _builder = new StringConcatenation();
    StringConcatenation _header = this.header(this);
    _builder.append(_header, "");
    _builder.newLineIfNotEmpty();
    _builder.append("package ");
    String _feature_package = GeneratorUtil.feature_package();
    _builder.append(_feature_package, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.eclipse.emf.ecore.EObject;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.IFeatureProvider;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.IReason;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.context.IUpdateContext;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.impl.Reason;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.mm.algorithms.Text;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.mm.pictograms.PictogramElement;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.mm.pictograms.Shape;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.services.IGaService;");
    _builder.newLine();
    _builder.append("import org.eclipselabs.spray.runtime.graphiti.features.AbstractUpdateFeature;");
    _builder.newLine();
    _builder.append("// MARKER_IMPORT");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public abstract class ");
    _builder.append(className, "");
    _builder.append(" extends AbstractUpdateFeature {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    StringConcatenation _generate_additionalFields = this.generate_additionalFields(reference);
    _builder.append(_generate_additionalFields, "    ");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("public ");
    _builder.append(className, "    ");
    _builder.append("(IFeatureProvider fp) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("super(fp);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("gaService = ");
    Diagram _diagram = this._metaReferenceExtensions.getDiagram(reference);
    String _activatorClassName = this._namingExtensions.getActivatorClassName(_diagram);
    String _shortName = this._namingExtensions.shortName(_activatorClassName);
    _builder.append(_shortName, "        ");
    _builder.append(".get(IGaService.class);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    StringConcatenation _generate_canUpdate = this.generate_canUpdate(reference);
    _builder.append(_generate_canUpdate, "    ");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    StringConcatenation _generate_updateNeeded = this.generate_updateNeeded(reference);
    _builder.append(_generate_updateNeeded, "    ");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    StringConcatenation _generate_update = this.generate_update(reference);
    _builder.append(_generate_update, "    ");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    StringConcatenation _generate_additionalMethods = this.generate_additionalMethods(reference);
    _builder.append(_generate_additionalMethods, "    ");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public StringConcatenation generate_canUpdate(final MetaReference reference) {
    StringConcatenation _builder = new StringConcatenation();
    StringConcatenation _overrideHeader = this.overrideHeader();
    _builder.append(_overrideHeader, "");
    _builder.newLineIfNotEmpty();
    _builder.append("public boolean canUpdate(IUpdateContext context) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("// return true, if linked business object is a EClass");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("EObject bo =  getBusinessObjectForPictogramElement(context.getPictogramElement());");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return (bo instanceof ");
    String _javaInterfaceName = this._namingExtensions.getJavaInterfaceName(this.target);
    String _shortName = this._namingExtensions.shortName(_javaInterfaceName);
    _builder.append(_shortName, "    ");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public StringConcatenation generate_updateNeeded(final MetaReference reference) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("   ");
    StringConcatenation _overrideHeader = this.overrideHeader();
    _builder.append(_overrideHeader, "   ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("public IReason updateNeeded(IUpdateContext context) {");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("// retrieve name from pictogram model");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("String pictogramName = null;");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("PictogramElement pictogramElement = context.getPictogramElement();");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("if (pictogramElement instanceof Shape) {");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("Shape cs = (Shape) pictogramElement;");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("if (cs.getGraphicsAlgorithm() instanceof Text) {");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("Text text = (Text) cs.getGraphicsAlgorithm();");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("// peService.getPropertyValue(shape, \"REFERENCE\");");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("if( pictogramName == null ){");
    _builder.newLine();
    _builder.append("                   ");
    _builder.append("pictogramName = text.getValue();");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("       ");
    _builder.append("// retrieve name from business model");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("String businessName = null;");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("EObject bo = getBusinessObjectForPictogramElement(pictogramElement);");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("if (bo instanceof ");
    String _name = this.target.getName();
    _builder.append(_name, "       ");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("           ");
    String _name_1 = this.target.getName();
    _builder.append(_name_1, "           ");
    _builder.append(" reference = (");
    String _name_2 = this.target.getName();
    _builder.append(_name_2, "           ");
    _builder.append(") bo;");
    _builder.newLineIfNotEmpty();
    _builder.append("           ");
    _builder.append("businessName = reference.get");
    String _labelPropertyName = this._namingExtensions.getLabelPropertyName(reference);
    String _firstUpper = StringExtensions.toFirstUpper(_labelPropertyName);
    _builder.append(_firstUpper, "           ");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("       ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("       ");
    _builder.append("// update needed, if names are different");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("boolean updateNameNeeded =");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("((pictogramName == null && businessName != null) ||");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("(pictogramName != null && !pictogramName.equals(businessName)));");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("if (updateNameNeeded) {");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("return Reason.createTrueReason(\"Property Name is out of date\");");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("return Reason.createFalseReason();");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public StringConcatenation generate_update(final MetaReference reference) {
    StringConcatenation _builder = new StringConcatenation();
    StringConcatenation _overrideHeader = this.overrideHeader();
    _builder.append(_overrideHeader, "");
    _builder.newLineIfNotEmpty();
    _builder.append("public boolean update(IUpdateContext context) {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("// retrieve name from business model");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("String businessName = null;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("PictogramElement pictogramElement = context.getPictogramElement();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("EObject bo = getBusinessObjectForPictogramElement(pictogramElement);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("if (bo instanceof ");
    String _name = this.target.getName();
    _builder.append(_name, "    ");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    String _name_1 = this.target.getName();
    _builder.append(_name_1, "        ");
    _builder.append(" eClass = (");
    String _name_2 = this.target.getName();
    _builder.append(_name_2, "        ");
    _builder.append(") bo;");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("businessName = eClass.get");
    String _labelPropertyName = this._namingExtensions.getLabelPropertyName(reference);
    String _firstUpper = StringExtensions.toFirstUpper(_labelPropertyName);
    _builder.append(_firstUpper, "        ");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("// Set name in pictogram model");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("if (pictogramElement instanceof Shape) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Shape cs = (Shape) pictogramElement;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("if (cs.getGraphicsAlgorithm() instanceof Text) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("Text text = (Text) cs.getGraphicsAlgorithm();");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("text.setValue(businessName);");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("layoutPictogramElement(cs.getContainer().getContainer());");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}