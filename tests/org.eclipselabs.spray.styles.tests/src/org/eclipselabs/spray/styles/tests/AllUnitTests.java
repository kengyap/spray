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
package org.eclipselabs.spray.styles.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite with all tests that are executable as plain unit tests (no plugin
 * test).
 */
@RunWith(Suite.class)
@SuiteClasses({ StyleFormatterTest.class, StyleModelTest.class,
		StyleScopingTest.class, StyleValidationTest.class, StyleGeneratorTest.class })
public class AllUnitTests {

}
