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

package org.netbeans.modules.web.core.jsploader;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.NbBundle;
import org.netbeans.api.java.loaders.JavaDataSupport;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.FileEntry;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;

/** The DataLoader for servlets (JavaDataObjects), which have been generated by JaSPer
* from JSP pages. Recognizes by string <code>_jsp_</code> in the name of the file.
* Does not recognize associated .class files, which are recognized as a separate ClassDataObject
*
* @author Petr Jiricka
*/
public final class JspServletDataLoader extends MultiFileLoader {

    /** serialVersionUID */
    private static final long serialVersionUID = -6033464827752236719L;

    /** The standard extension for Java source files. */
    public static final String JAVA_EXTENSION = "java"; // NOI18N
    
    public static final String JSP_MARK = "_jsp";   // NOI18N

    /** Creates a new JspServletDataLoader
    * Should <em>not</em> be used by subclasses.
     */ 
    public JspServletDataLoader() {
        super("org.netbeans.modules.web.core.jsploader.JspServletDataObject"); // NOI18N
    }
    
    /** Gets default display name. Overrides superclass mthod. */
    protected @Override String defaultDisplayName() {
        return NbBundle.getBundle(JspServletDataLoader.class).getString("PROP_JspServletLoader_Name");
    }
    
    @Override
    protected String actionsContext() {
        return "Loaders/text/x-jsp-servlet/Actions/"; // NOI18N
    }

    /** For a given file finds a primary file.
    * @param fo the file to find primary file for
    * @return the primary file for the file or null if the file is not
    *   recognized by this loader
    */
    protected FileObject findPrimaryFile (FileObject fo) {
        // detects  *_jsp*.java
        FileObject javaPrim;
        // never recognize folders.
        if (fo.isFolder())
            javaPrim = null;
        else if (fo.getExt().equals(JAVA_EXTENSION))
            javaPrim = fo;
        else
            javaPrim = null;
        
        if (javaPrim == null)
            return null;
        
        // if there is a source JSP set then this is generated from a JSP
        if (javaPrim.getAttribute(JspServletDataObject.EA_ORIGIN_JSP_PAGE) != null) {
            return javaPrim;
        }

        // PENDING: old way of recognition was by name, need to remove this later
        //if (javaPrim.getName().lastIndexOf(JSP_MARK) != -1)
        //    return javaPrim;
            
        return null;
    }

    /** Create the <code>JspServletDataObject</code>.
    * @param primaryFile the primary file
    * @return the data object for this file
    * @exception DataObjectExistsException if the primary file already has a data object
    */
    protected MultiDataObject createMultiObject (FileObject primaryFile)
    throws DataObjectExistsException, java.io.IOException {
        return new JspServletDataObject(primaryFile, this);
    }

    /** Create the primary file entry.
    * @param primaryFile primary file recognized by this loader
    * @return primary entry for that file
    */
    protected MultiDataObject.Entry createPrimaryEntry (MultiDataObject obj, FileObject primaryFile) {
        if (JAVA_EXTENSION.equals(primaryFile.getExt())) {
            return JavaDataSupport.createJavaFileEntry(obj, primaryFile);
        }
        else {
            return new FileEntry(obj, primaryFile);
        }
    }

    /** Create a secondary file entry.
    * By default, {@link FileEntry.Numb} is used for the class files
    *
    * @param secondaryFile secondary file to create entry for
    * @return the entry
    */
    protected MultiDataObject.Entry createSecondaryEntry (MultiDataObject obj, FileObject secondaryFile) {
        //The JavaDataObject itself has no secondary entries, but its subclasses have.
        //So we have to keep it as MultiFileLoader
        Logger.getLogger("global").log(Level.INFO, "Subclass of JavaDataLoader (" + this.getClass().getName() + ") has secondary entries but does not override createSecondaryEntries (MultidataObject, FileObject) method."); // NOI18N
        return new FileEntry.Numb(obj, secondaryFile);
    }   

}
