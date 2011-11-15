package org.eclipselabs.spray.generator.graphiti.templates.features;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.eclipselabs.spray.generator.graphiti.templates.FileGenerator;
import org.eclipselabs.spray.generator.graphiti.templates.JavaGenFile;
import org.eclipselabs.spray.generator.graphiti.util.GeneratorUtil;
import org.eclipselabs.spray.generator.graphiti.util.ImportUtil;
import org.eclipselabs.spray.generator.graphiti.util.NamingExtensions;
import org.eclipselabs.spray.mm.spray.Diagram;
import org.eclipselabs.spray.mm.spray.MetaClass;
import org.eclipselabs.spray.mm.spray.MetaReference;

@SuppressWarnings("all")
public class CreateReferenceAsConnectionFeature extends FileGenerator {
  @Inject
  private ImportUtil _importUtil;
  
  @Inject
  private NamingExtensions _namingExtensions;
  
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
    _builder.append(" ");
    StringConcatenation _extensionHeader = this.extensionHeader(this);
    _builder.append(_extensionHeader, " ");
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
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public StringConcatenation mainFile(final MetaReference reference, final String className) {
    StringConcatenation _builder = new StringConcatenation();
    EReference _target = reference.getTarget();
    final EReference target = _target;
    _builder.append(" ");
    _builder.newLineIfNotEmpty();
    MetaClass _metaClass = reference.getMetaClass();
    Diagram _diagram = _metaClass.getDiagram();
    String _name = _diagram.getName();
    final String diagramName = _name;
    _builder.newLineIfNotEmpty();
    StringConcatenation _header = this.header(this);
    _builder.append(_header, "");
    _builder.newLineIfNotEmpty();
    _builder.append("package ");
    String _feature_package = GeneratorUtil.feature_package();
    _builder.append(_feature_package, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.IFeatureProvider;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.context.ICreateConnectionContext;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.context.IContext;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.context.impl.AddConnectionContext;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.mm.pictograms.Anchor;");
    _builder.newLine();
    _builder.append("import org.eclipse.graphiti.mm.pictograms.Connection;");
    _builder.newLine();
    _builder.append("// MARKER_IMPORT");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(className, "");
    _builder.append(" extends AbstractCreateConnectionFeature {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ");
    _builder.append(className, "    ");
    _builder.append("(IFeatureProvider fp) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("// provide name and description for the UI, e.g. the palette");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("super(fp, \"");
    String _name_1 = this._namingExtensions.getName(reference);
    _builder.append(_name_1, "        ");
    _builder.append("\", \"Create ");
    String _name_2 = this._namingExtensions.getName(reference);
    _builder.append(_name_2, "        ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public boolean canCreate(ICreateConnectionContext context) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// return true if both anchors belong to an EClass");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// and those EClasses are not identical");
    _builder.newLine();
    _builder.append("        ");
    MetaClass _metaClass_1 = reference.getMetaClass();
    String _javaInterfaceName = this._namingExtensions.getJavaInterfaceName(_metaClass_1);
    String _shortName = this.shortName(_javaInterfaceName);
    _builder.append(_shortName, "        ");
    _builder.append(" source = get");
    MetaClass _metaClass_2 = reference.getMetaClass();
    String _name_3 = this._namingExtensions.getName(_metaClass_2);
    _builder.append(_name_3, "        ");
    _builder.append("(context.getSourceAnchor());");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    EClass _eReferenceType = target.getEReferenceType();
    String _javaInterfaceName_1 = this._namingExtensions.getJavaInterfaceName(_eReferenceType);
    String _shortName_1 = this.shortName(_javaInterfaceName_1);
    _builder.append(_shortName_1, "        ");
    _builder.append(" target = get");
    String _name_4 = target.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_4);
    _builder.append(_firstUpper, "        ");
    _builder.append("(context.getTargetAnchor());");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("if ( (source != null) && (target != null) && (source != target) ) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public boolean canStartConnection(ICreateConnectionContext context) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// return true if start anchor belongs to a EClass");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("if (get");
    MetaClass _metaClass_3 = reference.getMetaClass();
    String _name_5 = this._namingExtensions.getName(_metaClass_3);
    _builder.append(_name_5, "        ");
    _builder.append("(context.getSourceAnchor()) != null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public Connection create(ICreateConnectionContext context) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Connection newConnection = null;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// get EClasses which should be connected");
    _builder.newLine();
    _builder.append("        ");
    MetaClass _metaClass_4 = reference.getMetaClass();
    String _name_6 = this._namingExtensions.getName(_metaClass_4);
    _builder.append(_name_6, "        ");
    _builder.append(" source = get");
    MetaClass _metaClass_5 = reference.getMetaClass();
    String _name_7 = this._namingExtensions.getName(_metaClass_5);
    _builder.append(_name_7, "        ");
    _builder.append("(context.getSourceAnchor());");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    EClass _eReferenceType_1 = target.getEReferenceType();
    String _name_8 = _eReferenceType_1.getName();
    _builder.append(_name_8, "        ");
    _builder.append(" target = get");
    String _name_9 = target.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_9);
    _builder.append(_firstUpper_1, "        ");
    _builder.append("(context.getTargetAnchor());");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("if (source != null && target != null) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("// create new business object");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("set");
    String _name_10 = target.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_10);
    _builder.append(_firstUpper_2, "            ");
    _builder.append("(source, target);");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("// add connection for business object");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("AddConnectionContext addContext = new AddConnectionContext( context.getSourceAnchor(), context.getTargetAnchor());");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("addContext.setNewObject(source);");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("addContext.putProperty(\"REFERENCE\", \"");
    String _name_11 = this._namingExtensions.getName(reference);
    _builder.append(_name_11, "            ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("// TODO: assume that the target object has a Name property");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("//        addContext.putProperty(\"TARGETOBJECT\", target.getName());");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return newConnection;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("* Returns the ");
    MetaClass _metaClass_6 = reference.getMetaClass();
    String _name_12 = this._namingExtensions.getName(_metaClass_6);
    _builder.append(_name_12, "     ");
    _builder.append(" belonging to the anchor, or null if not available.");
    _builder.newLineIfNotEmpty();
    _builder.append("     ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("protected ");
    MetaClass _metaClass_7 = reference.getMetaClass();
    String _name_13 = this._namingExtensions.getName(_metaClass_7);
    _builder.append(_name_13, "    ");
    _builder.append(" get");
    MetaClass _metaClass_8 = reference.getMetaClass();
    String _name_14 = this._namingExtensions.getName(_metaClass_8);
    _builder.append(_name_14, "    ");
    _builder.append("(Anchor anchor) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("if (anchor != null) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("Object object = getBusinessObjectForPictogramElement(anchor.getParent());");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("if (object instanceof ");
    MetaClass _metaClass_9 = reference.getMetaClass();
    String _name_15 = this._namingExtensions.getName(_metaClass_9);
    _builder.append(_name_15, "            ");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("                ");
    _builder.append("return (");
    MetaClass _metaClass_10 = reference.getMetaClass();
    String _name_16 = this._namingExtensions.getName(_metaClass_10);
    _builder.append(_name_16, "                ");
    _builder.append(") object;");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    {
      MetaClass _metaClass_11 = reference.getMetaClass();
      String _name_17 = this._namingExtensions.getName(_metaClass_11);
      String _name_18 = target.getName();
      boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_name_17, _name_18);
      if (_operator_notEquals) {
        _builder.append("    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("* Returns the ");
        String _name_19 = target.getName();
        _builder.append(_name_19, "     ");
        _builder.append(" belonging to the anchor, or null if not available.");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("protected ");
        EClass _eReferenceType_2 = target.getEReferenceType();
        String _name_20 = _eReferenceType_2.getName();
        _builder.append(_name_20, "    ");
        _builder.append(" get");
        String _name_21 = target.getName();
        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_21);
        _builder.append(_firstUpper_3, "    ");
        _builder.append("(Anchor anchor) {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("    ");
        _builder.append("if (anchor != null) {");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("        ");
        _builder.append("Object object = getBusinessObjectForPictogramElement(anchor.getParent());");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("        ");
        _builder.append("if (object instanceof ");
        EClass _eReferenceType_3 = target.getEReferenceType();
        String _name_22 = _eReferenceType_3.getName();
        _builder.append(_name_22, "            ");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("            ");
        _builder.append("return (");
        EClass _eReferenceType_4 = target.getEReferenceType();
        String _name_23 = _eReferenceType_4.getName();
        _builder.append(_name_23, "                ");
        _builder.append(") object;");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("        ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("    ");
        _builder.append("return null;");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("    ");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("* Creates a ");
    String _name_24 = target.getName();
    _builder.append(_name_24, "     ");
    _builder.append(" .");
    _builder.newLineIfNotEmpty();
    _builder.append("     ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("protected void set");
    String _name_25 = target.getName();
    String _firstUpper_4 = StringExtensions.toFirstUpper(_name_25);
    _builder.append(_firstUpper_4, "    ");
    _builder.append("(");
    MetaClass _metaClass_12 = reference.getMetaClass();
    String _name_26 = this._namingExtensions.getName(_metaClass_12);
    _builder.append(_name_26, "    ");
    _builder.append(" source, ");
    EClass _eReferenceType_5 = target.getEReferenceType();
    String _name_27 = _eReferenceType_5.getName();
    _builder.append(_name_27, "    ");
    _builder.append(" target) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("// TODO Check multiplcity, if > 1, use addTo instead of set");
    _builder.newLine();
    {
      int _upperBound = target.getUpperBound();
      boolean _operator_equals = ObjectExtensions.operator_equals(((Integer)_upperBound), ((Integer)1));
      if (_operator_equals) {
        _builder.append("        ");
        _builder.append("source.set");
        String _name_28 = target.getName();
        String _firstUpper_5 = StringExtensions.toFirstUpper(_name_28);
        _builder.append(_firstUpper_5, "        ");
        _builder.append("(target);");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("        ");
        _builder.append("source.get");
        String _name_29 = target.getName();
        String _firstUpper_6 = StringExtensions.toFirstUpper(_name_29);
        _builder.append(_firstUpper_6, "        ");
        _builder.append("().add(target);");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
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
