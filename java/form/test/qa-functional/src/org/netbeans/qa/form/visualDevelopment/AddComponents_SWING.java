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
package org.netbeans.qa.form.visualDevelopment;

import org.netbeans.jellytools.*;
import org.netbeans.jellytools.modules.form.*;
import org.netbeans.jellytools.nodes.*;
import org.netbeans.jellytools.actions.*;

import java.util.*;
import org.netbeans.junit.ide.ProjectSupport;
import org.netbeans.qa.form.*;
import java.io.*;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;

/**
 *<P>
 *<B><BR> Test create frame.</B>
 *
 *<BR><BR><B>What it tests:</B><BR>
 *  Frame containing all components from Component Palette SWING category try compile.
 *<BR><BR><B>How it works:</B><BR>
 *  Find tested form file, add all components from SWING category and compile created frame (check compile resolution).
 *
 *<BR><BR><B>Settings:</B><BR>
 *  Jemmy/Jelly classes, VisualDevelopmentSupport class in the classpath.
 *
 *<BR><BR><B>Resources:</B><BR>
 *  File (Resources.) clear_Frame(java/form) generated by NBr32(37).
 *
 *<BR><B>Possible reasons of failure</B>
 * <BR><U>jelly didn't find menu or popup menu</U>
 * <BR><U>is impossible add component or components in SWING category is another as in NB r3.2 (37)</U>
 * <BR><U>component was't add correctly or generated source code is wrong</U>
 *
 * @author  Jana.Maleckova@czech.sun.com
 * @version
 * 
 * 
 * <b>Adam Senk</b>
 * 20 April 2011 WORKS
 */
public class AddComponents_SWING extends ExtJellyTestCase {

    public String FILE_NAME = "clear_JFrame.java";
    public String PACKAGE_NAME = "data";
    public String DATA_PROJECT_NAME = "SampleProject";
    public String FRAME_ROOT = "[JFrame]";
    FormDesignerOperator formDesigner;
    public MainWindowOperator mainWindow;
    public ProjectsTabOperator pto;
    public Node formnode;
    int icko;
    String[] componentList = {"Label", "Button", "Toggle Button", "Check Box", "Radio Button", "Combo Box", "Text Field", "Scroll Bar", "Slider", "Progress Bar", "Password Field", "Spinner", "Separator"};
    String[] componentList2 = {"Button Group", "List", "Tree", "Table", "Text Area", "Text Pane", "Editor Pane", "Formatted Field"};
    
    public AddComponents_SWING(String testName) {
        super(testName);
    }

    /** Run test.
     */
    @Override
    public void setUp() throws IOException {
        openDataProjects(_testProjectName);
    }

    public static Test suite() {
        return NbModuleSuite.create(NbModuleSuite.createConfiguration(AddComponents_SWING.class).addTest("testAddAndCompile").clusters(".*").enableModules(".*").gui(true));

    }

    /** Run test.
     */
    public void testAddAndCompile() {
        String categoryName = "Swing Controls";
        ArrayList<String> code=new ArrayList();
        pto = new ProjectsTabOperator();
        ProjectRootNode prn = pto.getProjectRootNode(DATA_PROJECT_NAME);
        prn.select();
        formnode = new Node(prn, "Source Packages|" + PACKAGE_NAME + "|" + FILE_NAME);
        formnode.select();
        log("Form node selected.");

        EditAction editAction = new EditAction();
        editAction.perform(formnode);
        log("Source Editor window opened.");

        OpenAction openAction = new OpenAction();
        openAction.perform(formnode);
        log("Form Editor window opened.");
        formDesigner = new FormDesignerOperator(FILE_NAME);
        formDesigner.source();
        formDesigner.design();
        // store all component names from the category in the Vector

        ComponentPaletteOperator palette = new ComponentPaletteOperator();
        palette.collapseBeans();
        palette.collapseSwingContainers();
        palette.collapseSwingMenus();
        palette.collapseSwingWindows();
        palette.collapseAWT();
        palette.expandSwingControls();
        //Read all simple swing componet

        //
        
        ComponentInspectorOperator cio = new ComponentInspectorOperator();
        Node inspectorRootNode = new Node(cio.treeComponents(), FRAME_ROOT);
        inspectorRootNode.select();
        inspectorRootNode.expand();

        // Add all beans from Swing Palette Category to form
        Action popupAddFromPaletteAction;
        for (int i = 0; i < componentList.length; i++) {
            ComponentInspectorOperator inspector = new ComponentInspectorOperator();
            icko = i;
            inspector.freezeNavigatorAndRun(new Runnable() {

                @Override
                public void run() {
                    ComponentInspectorOperator cio = new ComponentInspectorOperator();
                    Node inspectorRootNode = new Node(cio.treeComponents(), FRAME_ROOT);
                    runNoBlockPopupOverNode("Add From Palette|Swing Controls|" + componentList[icko], inspectorRootNode);
                }
            });

            String componentName = componentList[i].toString().replace(" ", "");
            System.out.println("What is searched: " + "private javax.swing.J" + componentName + " j" + componentName + "1");

            //formDesigner.source();
            // formDesigner.design();
            code.add("private javax.swing.J" + componentName + " j" + componentName + "1");
            //findInCode("private javax.swing.J" + componentName + " j" + componentName + "1", formDesigner);
            // findInCode("getContentPane().add(j" + componentName + "1)", formDesigner);
            //Check if code was generated properly for component
            //assertTrue("Check if " + componentName + " is correctly declared", checkEditor("private javax.swing.J" + componentName + " j" + componentName + "1"));
            //assertTrue("Check if " + componentName + " is added to layout", checkEditor("getContentPane().add(j" + componentName + "1)"));
        }
        openAction = new OpenAction();
        openAction.perform(formnode);
        log("Form Editor window opened.");
        formDesigner = new FormDesignerOperator(FILE_NAME);
        formDesigner.source();
        formDesigner.design();
        findInCode(code, formDesigner);
        code.clear();

        //Add the rest of swing components which inserted together with another component into layout
        

        for (int i = 0; i < componentList2.length; i++) {
            
            
            ComponentInspectorOperator inspector = new ComponentInspectorOperator();
            icko = i;
            inspector.freezeNavigatorAndRun(new Runnable() {

                @Override
                public void run() {
                    ComponentInspectorOperator cio = new ComponentInspectorOperator();
                    Node inspectorRootNode = new Node(cio.treeComponents(), FRAME_ROOT);
                    runNoBlockPopupOverNode("Add From Palette|Swing Controls|" + componentList2[icko], inspectorRootNode);
                }
            });
            
            String componentName = componentList2[i].toString().replace(" ", "");
            switch (i) {
                case 0:
                    System.out.println("private javax.swing." + componentName + " j" + componentName + "1");
                    //assertTrue("Check if " + componentName + " is correctly declared", checkEditor("private javax.swing." + componentName + " buttonGroup1"));
                    code.add("private javax.swing." + componentName + " buttonGroup1");
                    break;
                case 7:
                    //assertTrue("Check if " + componentName + " is correctly declared", checkEditor("private javax.swing.JFormattedTextField" + " jFormattedTextField1"));
                    //assertTrue("Check if " + componentName + " is added to layout", checkEditor("getContentPane().add(jFormattedTextField1)"));
                    code.add("private javax.swing.JFormattedTextField" + " jFormattedTextField1");
                    code.add("getContentPane().add(jFormattedTextField1)");
                    System.out.println("private javax.swing.J" + componentName + " j" + componentName + "1");
                    break;
                default:
                    System.out.println("private javax.swing.J" + componentName + " j" + componentName + "1");
                    //assertTrue("Check if " + componentName + " is correctly declared", checkEditor("private javax.swing.J" + componentName + " j" + componentName + "1"));
                    //assertTrue("Check if " + componentName + " is added to jScrollPane", checkEditor("jScrollPane" + i + ".setViewportView(j" + componentName + "1)"));
                    //assertTrue("Check if jScrollPane" + i + "is added to layout", checkEditor("getContentPane().add(jScrollPane" + i));
                    code.add("private javax.swing.J" + componentName + " j" + componentName + "1");
                    code.add("jScrollPane" + i + ".setViewportView(j" + componentName + "1)");
                    code.add("getContentPane().add(jScrollPane" + i);
                    break;

            }
        }
        
        openAction = new OpenAction();
        openAction.perform(formnode);
        log("Form Editor window opened.");
        formDesigner = new FormDesignerOperator(FILE_NAME);
        formDesigner.source();
        formDesigner.design();
        findInCode(code, formDesigner);


        log("All components from Swing Controls Palette : " + categoryName + " - were added to " + FILE_NAME);

        log("Try to save the form.");
        new org.netbeans.jemmy.EventTool().waitNoEvent(1000);
        editAction.perform(formnode);
        Action saveAction;
        saveAction = new Action("File|Save", null);
        saveAction.perform();

    }

    boolean checkEditor(String regexp) {

        sleep(300);
//        String editortext = editor.getText();

        formDesigner = new FormDesignerOperator(FILE_NAME);
        String editortext = formDesigner.editor().getText();
        formDesigner.design();

        java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(regexp, ",");
        int pos = -1;
        boolean result = true;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            pos = editortext.indexOf(token, pos);
            if (pos == -1) {
                result = false;
                break;
            }
            pos += token.length();
        }
        System.out.println("Result: " + result);
        return result;
    }

    public void closeDataProject() {
        ProjectSupport.closeProject(DATA_PROJECT_NAME);
        log("SampleProject closed.");
    }

    void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }
}
