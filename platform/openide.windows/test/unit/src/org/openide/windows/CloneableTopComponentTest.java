/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.openide.windows;

import java.awt.GraphicsEnvironment;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.windows.CloneableTopComponent.Ref;

public class CloneableTopComponentTest extends NbTestCase {
    
    public static Test suite() {
        return GraphicsEnvironment.isHeadless() ? new TestSuite() : new TestSuite(CloneableTopComponentTest.class);
    }

    public CloneableTopComponentTest(String n) {
        super(n);
    }

    public void testOpenCloseOpen() throws Exception {
        CloneableTopComponent c = new CloneableTopComponent(){
            @Override
            public int getPersistenceType() {
                return PERSISTENCE_NEVER;
            }
        };
        Ref ref = c.getReference();
        assertEquals("C is registered in the reference", c, ref.getArbitraryComponent());
        c.open();
        assertEquals("C is still registered", c, ref.getArbitraryComponent());
        assertTrue("Can be closed", c.close());
        assertNull("Set of references is empty", ref.getArbitraryComponent());
        c.open();
        assertEquals("Reference remains the same", ref, c.getReference());
        assertEquals("C is registered again", c, ref.getArbitraryComponent());
        c.close();
        assertNull("Not there again", ref.getArbitraryComponent());
    }
}
