/** ****************************************************************************
 * Copyright (c)  The Spray Project.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Spray Dev Team - initial API and implementation
 **************************************************************************** */
package org.eclipselabs.spray.shapes.generator;

import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.service.AbstractGenericModule;
import org.eclipselabs.spray.xtext.generator.IPostProcessor;
import org.eclipselabs.spray.xtext.generator.filesystem.JavaIoFileSystemAccessExt;
import org.eclipselabs.spray.xtext.generator.formatting.CodeFormatterProvider;
import org.eclipselabs.spray.xtext.generator.formatting.JavaPostProcessor;
import org.eclipselabs.spray.xtext.generator.importmanager.ImportUtil;
import org.eclipselabs.spray.xtext.generator.outputconfig.SprayOutputConfigurationProvider;

import com.google.inject.Binder;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class ShapesGeneratorModule extends AbstractGenericModule {
    @Override
    public void configure(Binder binder) {
        super.configure(binder);
        binder.bind(ImportUtil.class).in(Scopes.SINGLETON);
    }

    public Class<? extends org.eclipse.xtext.generator.IGenerator> bindIGenerator() {
        return ShapeGenerator.class;
    }

    public Class<? extends IFileSystemAccess> bindJavaIoFileSystemAccess() {
        return JavaIoFileSystemAccessExt.class;
    }

    public void configureCodeFormatterProvider(Binder binder) {
        binder.bind(CodeFormatter.class).toProvider(CodeFormatterProvider.class);
    }

    public void configureJavaPostProcessor(Binder binder) {
        binder.bind(IPostProcessor.class).annotatedWith(Names.named("java")).to(JavaPostProcessor.class);
    }

    public void configureJavaFormatterConfig(Binder binder) {
        binder.bind(String.class).annotatedWith(Names.named(CodeFormatterProvider.JDT_FORMATTER_CONFIG)).toInstance("org/eclipselabs/spray/xtext/generator/formatting/formatter.xml");
    }

    public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
        return SprayOutputConfigurationProvider.class;
    }
}
