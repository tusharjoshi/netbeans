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

package org.netbeans.modules.java.source.parsing;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.ChangeSupport;

/**
 *
 * @author Tomas Zezula
 */
class ClasspathInfoListener implements ChangeListener {
    
    private final ChangeSupport changeSupport;
    private final Runnable callBack;
    
    ClasspathInfoListener (
            @NonNull final ChangeSupport changedSupport,
            @NullAllowed final Runnable callBack) {
        assert changedSupport != null;
        this.changeSupport = changedSupport;
        this.callBack = callBack;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (callBack != null) {
            callBack.run();
        }
        this.changeSupport.fireChange();
    }        
}
