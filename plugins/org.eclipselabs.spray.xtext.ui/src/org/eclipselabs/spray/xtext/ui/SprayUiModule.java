/*
 * generated by Xtext
 */
package org.eclipselabs.spray.xtext.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess;
import org.eclipse.xtext.ui.editor.model.TokenTypeToStringMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipselabs.spray.xtext.ui.generator.EclipseResourceFileSystemAccessExt;
import org.eclipselabs.spray.xtext.ui.syntaxcoloring.SprayHighlightingConfiguration;
import org.eclipselabs.spray.xtext.ui.syntaxcoloring.SprayTokenToAttributeIdMapper;
import org.eclipselabs.spray.xtext.ui.wizard.SprayProjectCreator;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used within the IDE.
 */
public class SprayUiModule extends AbstractSprayUiModule {
    /** Key for String Binding */
    public static final String NEW_PROJECT_NAME = "org.eclipselabs.spray.xtext.ui.newProjectName";

    public SprayUiModule(AbstractUIPlugin plugin) {
        super(plugin);
    }

    public Class<? extends AbstractAntlrTokenToAttributeIdMapper> bindAbstractAntlrTokenToAttributeIdMapper() {
        return SprayTokenToAttributeIdMapper.class;
    }

    @Override
    public void configure(Binder binder) {
        super.configure(binder);
        binder.bind(TokenTypeToStringMapper.class).to(SprayTokenToAttributeIdMapper.class);
        binder.bind(IHighlightingConfiguration.class).to(SprayHighlightingConfiguration.class);
    }

    public void configureNewProjectName(Binder binder) {
        binder.bind(String.class).annotatedWith(Names.named(NEW_PROJECT_NAME)).toInstance("org.xspray.examples.MyDiagram");
    }
    
    public Class<? extends EclipseResourceFileSystemAccess> bindEclipseResourceFileSystemAccess () {
    	return EclipseResourceFileSystemAccessExt.class;
    }
    
    public Class<? extends IProjectCreator> bindIProjectCreator () {
    	return SprayProjectCreator.class;
    }
}