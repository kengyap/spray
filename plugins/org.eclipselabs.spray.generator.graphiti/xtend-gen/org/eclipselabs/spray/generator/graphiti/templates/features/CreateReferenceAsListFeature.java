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
import org.eclipselabs.spray.mm.spray.Container;
import org.eclipselabs.spray.mm.spray.MetaClass;
import org.eclipselabs.spray.mm.spray.MetaReference;

@SuppressWarnings("all")
public class CreateReferenceAsListFeature extends FileGenerator {
  @Inject
  private NamingExtensions _namingExtensions;
  
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
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public StringConcatenation mainFile(final MetaReference reference, final String className) {
    StringConcatenation _builder = new StringConcatenation();
    EObject _eContainer = reference.eContainer();
    MetaClass _represents = ((Container) _eContainer).getRepresents();
    final MetaClass metaClass = _represents;
    _builder.newLineIfNotEmpty();
    StringConcatenation _header = this.header(this);
    _builder.append(_header, "");
    _builder.newLineIfNotEmpty();
    _builder.append("package ");
    String _feature_package = GeneratorUtil.feature_package();
    _builder.append(_feature_package, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import org.eclipse.graphiti.features.IFeatureProvider;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.context.ICreateContext;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.context.IContext;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.impl.AbstractCreateFeature;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.mm.pictograms.Shape;");
    _builder.newLine();
    _builder.append("import ");
    String _util_package = GeneratorUtil.util_package();
    _builder.append(_util_package, "");
    _builder.append(".SampleUtil;");
    _builder.newLineIfNotEmpty();
    _builder.append("// MARKER_IMPORT");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(className, "");
    _builder.append(" extends AbstractCreateFeature {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final String TITLE = \"Create ");
    String _name = this.target.getName();
    _builder.append(_name, "    ");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("private static final String USER_QUESTION = \"Enter new ");
    String _name_1 = this.target.getName();
    _builder.append(_name_1, "    ");
    _builder.append(" ");
    String _labelPropertyName = this._namingExtensions.getLabelPropertyName(reference);
    _builder.append(_labelPropertyName, "    ");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ");
    _builder.append(className, "    ");
    _builder.append("(IFeatureProvider fp) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("// set name and description of the creation feature");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("super(fp, \"");
    String _name_2 = this.target.getName();
    _builder.append(_name_2, "        ");
    _builder.append("\", \"Create ");
    String _name_3 = this.target.getName();
    _builder.append(_name_3, "        ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public boolean canCreate(ICreateContext context) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Shape target = context.getTargetContainer();");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Object domainObject = getBusinessObjectForPictogramElement(target);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return domainObject instanceof ");
    String _name_4 = this._namingExtensions.getName(metaClass);
    _builder.append(_name_4, "        ");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public Object[] create(ICreateContext context) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// ask user for ");
    String _name_5 = this.target.getName();
    _builder.append(_name_5, "        ");
    _builder.append(" name");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("String newName = SampleUtil.askString(TITLE, USER_QUESTION, \"\");");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("if (newName == null || newName.trim().length() == 0) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return EMPTY;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append(" ");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("Shape target = context.getTargetContainer();");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Object domainObject = getBusinessObjectForPictogramElement(target);");
    _builder.newLine();
    _builder.append("        ");
    String _javaInterfaceName = this._namingExtensions.getJavaInterfaceName(metaClass);
    String _shortName = this.shortName(_javaInterfaceName);
    _builder.append(_shortName, "        ");
    _builder.append(" owner = (");
    String _name_6 = this._namingExtensions.getName(metaClass);
    _builder.append(_name_6, "        ");
    _builder.append(")domainObject;");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// create ");
    String _name_7 = this.target.getName();
    _builder.append(_name_7, "        ");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    String _javaInterfaceName_1 = this._namingExtensions.getJavaInterfaceName(this.target);
    String _shortName_1 = this.shortName(_javaInterfaceName_1);
    _builder.append(_shortName_1, "        ");
    _builder.append(" newDomainObject = ");
    String _eFactoryInterfaceName = this._namingExtensions.getEFactoryInterfaceName(metaClass);
    String _shortName_2 = this.shortName(_eFactoryInterfaceName);
    _builder.append(_shortName_2, "        ");
    _builder.append(".eINSTANCE.create");
    String _name_8 = this.target.getName();
    _builder.append(_name_8, "        ");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("newDomainObject.set");
    String _labelPropertyName_1 = this._namingExtensions.getLabelPropertyName(reference);
    String _firstUpper = StringExtensions.toFirstUpper(_labelPropertyName_1);
    _builder.append(_firstUpper, "        ");
    _builder.append("(newName);");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("owner.get");
    String _name_9 = this._namingExtensions.getName(reference);
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_9);
    _builder.append(_firstUpper_1, "        ");
    _builder.append("().add(newDomainObject);");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// do the add");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("addGraphicalRepresentation(context, newDomainObject);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// return newly created business object(s)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return new Object[] { newDomainObject };");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public boolean hasDoneChanges() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public boolean canUndo(IContext context) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
