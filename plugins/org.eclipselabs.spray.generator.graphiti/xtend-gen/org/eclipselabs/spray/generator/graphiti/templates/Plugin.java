package org.eclipselabs.spray.generator.graphiti.templates;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipselabs.spray.generator.graphiti.templates.TemplateUtil;
import org.eclipselabs.spray.generator.graphiti.util.GeneratorUtil;
import org.eclipselabs.spray.generator.graphiti.util.NamingExtensions;
import org.eclipselabs.spray.generator.graphiti.util.XtendProperties;
import org.eclipselabs.spray.mm.spray.Container;
import org.eclipselabs.spray.mm.spray.Diagram;
import org.eclipselabs.spray.mm.spray.MetaClass;
import org.eclipselabs.spray.mm.spray.MetaReference;
import org.eclipselabs.spray.mm.spray.Shape;
import org.eclipselabs.spray.mm.spray.SprayElement;

@SuppressWarnings("all")
public class Plugin extends TemplateUtil {
  @Inject
  private NamingExtensions _namingExtensions;
  
  public CharSequence generate(final Diagram diagram) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("        ");
    String _name = diagram.getName();
    final String diagramName = _name;
    _builder.newLineIfNotEmpty();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<?eclipse version=\"3.0\"?>");
    _builder.newLine();
    _builder.append("        ");
    CharSequence _pluginHeader = this.pluginHeader(this);
    _builder.append(_pluginHeader, "        ");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("<!-- Potentially redefine the EDITOR EXTENSION TO ALLOW FOR OUR OWN EDITOR SUBCLASS TO BE USED. ");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("                 ");
    _builder.append("point=\"org.eclipse.ui.editors\">");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("<editor");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("class=\"");
    String _extensionFactoryClassName = this._namingExtensions.getExtensionFactoryClassName(diagram);
    _builder.append(_extensionFactoryClassName, "                  ");
    _builder.append(":");
    String _diagramEditorClassName = this._namingExtensions.getDiagramEditorClassName(diagram);
    _builder.append(_diagramEditorClassName, "                  ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("                  ");
    _builder.append("contributorClass=\"org.eclipse.graphiti.ui.editor.DiagramEditorActionBarContributor\"");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("default=\"true\"");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("extensions=\"diagram\"");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("icon=\"icons/diagram.gif\"");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("id=\"");
    String _diagramEditorClassName_1 = this._namingExtensions.getDiagramEditorClassName(diagram);
    _builder.append(_diagramEditorClassName_1, "                  ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("                  ");
    _builder.append("matchingStrategy=\"org.eclipse.graphiti.ui.editor.DiagramEditorFactory$DiagramEditorMatchingStrategy\"");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("name=\"%_diagram_editor_name\">");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("<contentTypeBinding");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("contentTypeId=\"org.eclipse.graphiti.content.diagram\">");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("</contentTypeBinding>");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("</editor>");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("-->");
    _builder.newLine();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("point=\"org.eclipse.graphiti.ui.diagramTypes\">");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("<diagramType");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("description=\"This is the diagram type for the ");
    _builder.append(diagramName, "              ");
    _builder.append(" diagram type\"");
    _builder.newLineIfNotEmpty();
    _builder.append("              ");
    _builder.append("id=\"");
    String _diagram_package = GeneratorUtil.diagram_package();
    _builder.append(_diagram_package, "              ");
    _builder.append(".");
    String _firstUpper = StringExtensions.toFirstUpper(diagramName);
    _builder.append(_firstUpper, "              ");
    _builder.append("DiagramType\"");
    _builder.newLineIfNotEmpty();
    _builder.append("              ");
    _builder.append("name=\"");
    _builder.append(diagramName, "              ");
    _builder.append(" Graphiti Diagram Type\"");
    _builder.newLineIfNotEmpty();
    _builder.append("              ");
    _builder.append("type=\"");
    _builder.append(diagramName, "              ");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("</diagramType>");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("point=\"org.eclipse.graphiti.ui.diagramTypeProviders\">");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("<diagramTypeProvider");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("class=\"");
    String _extensionFactoryClassName_1 = this._namingExtensions.getExtensionFactoryClassName(diagram);
    _builder.append(_extensionFactoryClassName_1, "              ");
    _builder.append(":");
    String _diagramTypeProviderClassName = this._namingExtensions.getDiagramTypeProviderClassName(diagram);
    _builder.append(_diagramTypeProviderClassName, "              ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("              ");
    _builder.append("description=\"This is my editor for the ");
    _builder.append(diagramName, "              ");
    _builder.append(" diagram type\"");
    _builder.newLineIfNotEmpty();
    _builder.append("              ");
    _builder.append("id=\"");
    String _diagramTypeProviderClassName_1 = this._namingExtensions.getDiagramTypeProviderClassName(diagram);
    _builder.append(_diagramTypeProviderClassName_1, "              ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("              ");
    _builder.append("name=\"");
    _builder.append(diagramName, "              ");
    _builder.append(" editor\">");
    _builder.newLineIfNotEmpty();
    _builder.append("              ");
    _builder.append("<diagramType");
    _builder.newLine();
    _builder.append("                    ");
    _builder.append("id=\"");
    String _diagram_package_1 = GeneratorUtil.diagram_package();
    _builder.append(_diagram_package_1, "                    ");
    _builder.append(".");
    String _firstUpper_1 = StringExtensions.toFirstUpper(diagramName);
    _builder.append(_firstUpper_1, "                    ");
    _builder.append("DiagramType\">");
    _builder.newLineIfNotEmpty();
    _builder.append("              ");
    _builder.append("</diagramType>");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("<imageProvider");
    _builder.newLine();
    _builder.append("                       ");
    _builder.append("id=\"");
    String _imageProviderClassName = this._namingExtensions.getImageProviderClassName(diagram);
    _builder.append(_imageProviderClassName, "                       ");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("                 ");
    _builder.append("</imageProvider>");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("</diagramTypeProvider>");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("                 ");
    _builder.append("point=\"org.eclipse.graphiti.ui.imageProviders\">");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("<imageProvider");
    _builder.newLine();
    _builder.append("                    ");
    _builder.append("class=\"");
    String _extensionFactoryClassName_2 = this._namingExtensions.getExtensionFactoryClassName(diagram);
    _builder.append(_extensionFactoryClassName_2, "                    ");
    _builder.append(":");
    String _imageProviderClassName_1 = this._namingExtensions.getImageProviderClassName(diagram);
    _builder.append(_imageProviderClassName_1, "                    ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("                       ");
    _builder.append("id=\"");
    String _imageProviderClassName_2 = this._namingExtensions.getImageProviderClassName(diagram);
    _builder.append(_imageProviderClassName_2, "                       ");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("              ");
    _builder.append("</imageProvider>");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("point=\"org.eclipse.ui.views.properties.tabbed.propertyContributor\">");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("<propertyContributor contributorId=\"");
    _builder.append(diagramName, "              ");
    _builder.append(".PropertyContributor\">");
    _builder.newLineIfNotEmpty();
    _builder.append("                  ");
    _builder.append("<propertyCategory category=\"");
    _builder.append(diagramName, "                  ");
    _builder.append("Category\">");
    _builder.newLineIfNotEmpty();
    _builder.append("                  ");
    _builder.append("</propertyCategory>");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("</propertyContributor>");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("point=\"org.eclipse.ui.views.properties.tabbed.propertyTabs\">");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("<propertyTabs contributorId=\"");
    _builder.append(diagramName, "              ");
    _builder.append(".PropertyContributor\">");
    _builder.newLineIfNotEmpty();
    _builder.append("                  ");
    _builder.append("<propertyTab label=\"Main\" category=\"");
    _builder.append(diagramName, "                  ");
    _builder.append("Category\"");
    _builder.newLineIfNotEmpty();
    _builder.append("                      ");
    _builder.append("id=\"");
    _builder.append(diagramName, "                      ");
    _builder.append(".main.tab\">");
    _builder.newLineIfNotEmpty();
    _builder.append("                  ");
    _builder.append("</propertyTab>");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("</propertyTabs>");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("</extension>");
    _builder.newLine();
    {
      MetaClass[] _metaClasses = diagram.getMetaClasses();
      for(final MetaClass cls : _metaClasses) {
        _builder.append("        ");
        XtendProperties.setValue("PreviousSection", null);
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("<extension");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("point=\"org.eclipse.ui.views.properties.tabbed.propertySections\">");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("<propertySections contributorId=\"");
        _builder.append(diagramName, "            ");
        _builder.append(".PropertyContributor\">");
        _builder.newLineIfNotEmpty();
        {
          EClass _type = cls.getType();
          EList<EAttribute> _eAllAttributes = _type.getEAllAttributes();
          for(final EAttribute property : _eAllAttributes) {
            _builder.append("        ");
            _builder.append("  ");
            _builder.append("<propertySection tab=\"");
            _builder.append(diagramName, "          ");
            _builder.append(".main.tab\"");
            _builder.newLineIfNotEmpty();
            _builder.append("        ");
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("class=\"");
            String _extensionFactoryClassName_3 = this._namingExtensions.getExtensionFactoryClassName(diagram);
            _builder.append(_extensionFactoryClassName_3, "           ");
            _builder.append(":");
            EClass _type_1 = cls.getType();
            String _propertySectionClassName = this._namingExtensions.getPropertySectionClassName(_type_1, property);
            _builder.append(_propertySectionClassName, "           ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("        ");
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("filter=\"");
            String _filterClassName = this._namingExtensions.getFilterClassName(cls);
            _builder.append(_filterClassName, "           ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            {
              String _value = XtendProperties.getValue("PreviousSection");
              boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_value, null);
              if (_operator_notEquals) {
                _builder.append("                 ");
                _builder.append("afterSection=\"");
                String _value_1 = XtendProperties.getValue("PreviousSection");
                _builder.append(_value_1, "                 ");
                _builder.append("\"");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("        ");
            _builder.append("  ");
            _builder.append(" ");
            String _operator_plus = StringExtensions.operator_plus(diagramName, ".main.tab.");
            String _name_1 = this._namingExtensions.getName(cls);
            String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, _name_1);
            String _operator_plus_2 = StringExtensions.operator_plus(_operator_plus_1, ".");
            String _name_2 = property.getName();
            String _operator_plus_3 = StringExtensions.operator_plus(_operator_plus_2, _name_2);
            XtendProperties.setValue("PreviousSection", _operator_plus_3);
            _builder.newLineIfNotEmpty();
            _builder.append("        ");
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("id=\"");
            String _value_2 = XtendProperties.getValue("PreviousSection");
            _builder.append(_value_2, "           ");
            _builder.append("\">");
            _builder.newLineIfNotEmpty();
            _builder.append("        ");
            _builder.append("  ");
            _builder.append("</propertySection>");
            _builder.newLine();
          }
        }
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("</propertySections>");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("</extension>");
        _builder.newLine();
      }
    }
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// Find all clases that are shown as lists in the compartments");
    _builder.newLine();
    {
      MetaClass[] _metaClasses_1 = diagram.getMetaClasses();
      for(final MetaClass cls_1 : _metaClasses_1) {
        {
          Shape _representedBy = cls_1.getRepresentedBy();
          if ((_representedBy instanceof Container)) {
            _builder.append("        ");
            Shape _representedBy_1 = cls_1.getRepresentedBy();
            Container container = ((Container) _representedBy_1);
            _builder.newLineIfNotEmpty();
            {
              SprayElement[] _parts = container.getParts();
              Iterable<MetaReference> _filter = IterableExtensions.<MetaReference>filter(((Iterable<? extends Object>)Conversions.doWrapArray(_parts)), org.eclipselabs.spray.mm.spray.MetaReference.class);
              for(final MetaReference ref : _filter) {
                _builder.append("        ");
                _builder.append("  ");
                String _name_3 = this._namingExtensions.getName(ref);
                XtendProperties.setValue("refName", _name_3);
                _builder.append(" ");
                _builder.newLineIfNotEmpty();
                _builder.append("        ");
                _builder.append("  ");
                EClass _type_2 = cls_1.getType();
                EList<EReference> _eAllReferences = _type_2.getEAllReferences();
                final EList<EReference> references = _eAllReferences;
                _builder.append(" ");
                _builder.newLineIfNotEmpty();
                _builder.append("        ");
                _builder.append("  ");
                EReference _target = ref.getTarget();
                final EReference target = _target;
                _builder.newLineIfNotEmpty();
                _builder.append("        ");
                _builder.append("    ");
                XtendProperties.setValue("PreviousSection", null);
                _builder.newLineIfNotEmpty();
                _builder.append("        ");
                _builder.append("  ");
                _builder.append("<extension");
                _builder.newLine();
                _builder.append("        ");
                _builder.append("    ");
                _builder.append("point=\"org.eclipse.ui.views.properties.tabbed.propertySections\">");
                _builder.newLine();
                _builder.append("        ");
                _builder.append("    ");
                _builder.append("<propertySections contributorId=\"");
                _builder.append(diagramName, "            ");
                _builder.append(".PropertyContributor\">");
                _builder.newLineIfNotEmpty();
                {
                  EClass _eReferenceType = target.getEReferenceType();
                  EList<EAttribute> _eAllAttributes_1 = _eReferenceType.getEAllAttributes();
                  for(final EAttribute attribute : _eAllAttributes_1) {
                    _builder.append("        ");
                    _builder.append("  ");
                    _builder.append("<propertySection tab=\"");
                    _builder.append(diagramName, "          ");
                    _builder.append(".main.tab\"           ");
                    _builder.newLineIfNotEmpty();
                    _builder.append("        ");
                    _builder.append("  ");
                    _builder.append(" ");
                    _builder.append("class=\"");
                    String _extensionFactoryClassName_4 = this._namingExtensions.getExtensionFactoryClassName(diagram);
                    _builder.append(_extensionFactoryClassName_4, "           ");
                    _builder.append(":");
                    String _property_package = GeneratorUtil.property_package();
                    _builder.append(_property_package, "           ");
                    _builder.append(".");
                    EClass _eReferenceType_1 = target.getEReferenceType();
                    String _name_4 = _eReferenceType_1.getName();
                    _builder.append(_name_4, "           ");
                    String _name_5 = attribute.getName();
                    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_5);
                    _builder.append(_firstUpper_2, "           ");
                    _builder.append("Section\"");
                    _builder.newLineIfNotEmpty();
                    _builder.append("        ");
                    _builder.append("  ");
                    _builder.append(" ");
                    _builder.append("filter=\"");
                    String _property_package_1 = GeneratorUtil.property_package();
                    _builder.append(_property_package_1, "           ");
                    _builder.append(".");
                    EClass _eReferenceType_2 = target.getEReferenceType();
                    String _name_6 = _eReferenceType_2.getName();
                    _builder.append(_name_6, "           ");
                    _builder.append("Filter\"");
                    _builder.newLineIfNotEmpty();
                    {
                      String _value_3 = XtendProperties.getValue("PreviousSection");
                      boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(_value_3, null);
                      if (_operator_notEquals_1) {
                        _builder.append("                         ");
                        _builder.append("afterSection=\"");
                        String _value_4 = XtendProperties.getValue("PreviousSection");
                        _builder.append(_value_4, "                         ");
                        _builder.append("\"");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                    _builder.append("        ");
                    _builder.append("  ");
                    _builder.append(" ");
                    String _operator_plus_4 = StringExtensions.operator_plus(diagramName, ".main.tab.");
                    EClass _eReferenceType_3 = target.getEReferenceType();
                    String _name_7 = _eReferenceType_3.getName();
                    String _operator_plus_5 = StringExtensions.operator_plus(_operator_plus_4, _name_7);
                    String _operator_plus_6 = StringExtensions.operator_plus(_operator_plus_5, ".");
                    String _name_8 = attribute.getName();
                    String _operator_plus_7 = StringExtensions.operator_plus(_operator_plus_6, _name_8);
                    XtendProperties.setValue("PreviousSection", _operator_plus_7);
                    _builder.newLineIfNotEmpty();
                    _builder.append("        ");
                    _builder.append("  ");
                    _builder.append(" ");
                    _builder.append("id=\"");
                    String _value_5 = XtendProperties.getValue("PreviousSection");
                    _builder.append(_value_5, "           ");
                    _builder.append("\">");
                    _builder.newLineIfNotEmpty();
                    _builder.append("        ");
                    _builder.append("  ");
                    _builder.append("</propertySection>");
                    _builder.newLine();
                  }
                }
                _builder.append("        ");
                _builder.append("    ");
                _builder.append("</propertySections>");
                _builder.newLine();
                _builder.append("        ");
                _builder.append("</extension>");
                _builder.newLine();
              }
            }
          }
        }
      }
    }
    _builder.append("        ");
    _builder.append("</plugin>");
    _builder.newLine();
    return _builder;
  }
}
