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
package org.netbeans.modules.form.layoutdesign;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.openide.filesystems.FileUtil;

public class ALT_GapsOptimization6Test extends LayoutTestCase {

    public ALT_GapsOptimization6Test(String name) {
        super(name);
        try {
            className = this.getClass().getName();
            className = className.substring(className.lastIndexOf('.') + 1, className.length());
            startingFormFile = FileUtil.toFileObject(new File(url.getFile() + goldenFilesPath + className + "-StartingForm.form").getCanonicalFile());
        } catch (IOException ioe) {
            fail(ioe.toString());
        }
    }

    /**
     * Resize jTetxField2 to the right to snap to container border.
     * The starting form has a problematic layout in vertical dimension where
     * the preferred gap below jTextField1 is ineffective because of another 
     * (explicit) gap above jCheckBox1 (it's sequential, but nested, so not
     * optimized). During the resizing operation the gaps come next to each
     * other in one sequence, and need to be mergerd at that point. (Used to be
     * optimized by the default after-build optimization whic lost the explicit
     * size so the gap visually collapsed.)
     */
    public void doChanges0() {
        ld.externalSizeChangeHappened();
// > UPDATE CURRENT STATE
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        contInterior.put("Form", new Rectangle(0, 0, 400, 300));
        compBounds.put("jTextField1", new Rectangle(0, 0, 400, 20));
        baselinePosition.put("jTextField1-400-20", new Integer(14));
        compBounds.put("jCheckBox2", new Rectangle(10, 43, 81, 23));
        baselinePosition.put("jCheckBox2-81-23", new Integer(15));
        compBounds.put("jScrollPane1", new Rectangle(10, 78, 35, 86));
        baselinePosition.put("jScrollPane1-35-86", new Integer(0));
        compBounds.put("jButton1", new Rectangle(78, 112, 73, 23));
        baselinePosition.put("jButton1-73-23", new Integer(15));
        compBounds.put("jToggleButton1", new Rectangle(78, 141, 105, 23));
        baselinePosition.put("jToggleButton1-105-23", new Integer(15));
        compBounds.put("jTextField2", new Rectangle(78, 81, 59, 20));
        baselinePosition.put("jTextField2-59-20", new Integer(14));
        compBounds.put("jScrollPane2", new Rectangle(355, 114, 35, 50));
        baselinePosition.put("jScrollPane2-35-50", new Integer(0));
        compMinSize.put("Form", new Dimension(234, 175));
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        compPrefSize.put("jTextField1", new Dimension(59, 20));
        prefPadding.put("jCheckBox2-jScrollPane2-0-0-0", new Integer(2)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jCheckBox2-jScrollPane2-0-0-1", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jCheckBox2-jScrollPane2-0-0-2", new Integer(21)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jCheckBox2-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-1", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-2", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-1", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-2", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jTextField2-jScrollPane2-0-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jTextField2-jScrollPane2-0-0-1", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jTextField2-jScrollPane2-0-0-2", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jTextField2-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPaddingInParent.put("Form-jScrollPane2-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jScrollPane1-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jToggleButton1-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        ld.updateCurrentState();
// < UPDATE CURRENT STATE
// > START RESIZING
        baselinePosition.put("jTextField2-59-20", new Integer(14));
        compPrefSize.put("jTextField2", new Dimension(59, 20));
        {
            String[] compIds = new String[]{
                "jTextField2"
            };
            Rectangle[] bounds = new Rectangle[]{
                new Rectangle(78, 81, 59, 20)
            };
            Point hotspot = new Point(137, 87);
            int[] resizeEdges = new int[]{
                1,
                -1
            };
            boolean inLayout = true;
            ld.startResizing(compIds, bounds, hotspot, resizeEdges, inLayout);
        }
// < START RESIZING
// > MOVE
        {
            Point p = new Point(403, 104);
            String containerId = "Form";
            boolean autoPositioning = true;
            boolean lockDimension = false;
            Rectangle[] bounds = new Rectangle[]{
                new Rectangle(78, 81, 322, 20)
            };
            ld.move(p, containerId, autoPositioning, lockDimension, bounds);
        }
// < MOVE
// > MOVE
        {
            Point p = new Point(404, 104);
            String containerId = "Form";
            boolean autoPositioning = true;
            boolean lockDimension = false;
            Rectangle[] bounds = new Rectangle[]{
                new Rectangle(78, 81, 322, 20)
            };
            ld.move(p, containerId, autoPositioning, lockDimension, bounds);
        }
// < MOVE
// > END MOVING
        compPrefSize.put("jTextField1", new Dimension(59, 20));
        prefPadding.put("jCheckBox2-jScrollPane2-0-0-0", new Integer(2)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jCheckBox2-jScrollPane2-0-0-1", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jCheckBox2-jScrollPane2-0-0-2", new Integer(21)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jCheckBox2-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-1", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-2", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-1", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-2", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPaddingInParent.put("Form-jScrollPane2-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jScrollPane1-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jToggleButton1-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        prefPadding.put("jTextField2-jButton1-1-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jTextField2-jScrollPane2-1-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        ld.endMoving(true);
// < END MOVING
        ld.externalSizeChangeHappened();
// > UPDATE CURRENT STATE
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        contInterior.put("Form", new Rectangle(0, 0, 400, 300));
        compBounds.put("jTextField1", new Rectangle(0, 0, 400, 20));
        baselinePosition.put("jTextField1-400-20", new Integer(14));
        compBounds.put("jCheckBox2", new Rectangle(10, 43, 81, 23));
        baselinePosition.put("jCheckBox2-81-23", new Integer(15));
        compBounds.put("jScrollPane1", new Rectangle(10, 78, 35, 86));
        baselinePosition.put("jScrollPane1-35-86", new Integer(0));
        compBounds.put("jButton1", new Rectangle(78, 112, 73, 23));
        baselinePosition.put("jButton1-73-23", new Integer(15));
        compBounds.put("jToggleButton1", new Rectangle(78, 141, 105, 23));
        baselinePosition.put("jToggleButton1-105-23", new Integer(15));
        compBounds.put("jTextField2", new Rectangle(78, 81, 322, 20));
        baselinePosition.put("jTextField2-322-20", new Integer(14));
        compBounds.put("jScrollPane2", new Rectangle(355, 114, 35, 50));
        baselinePosition.put("jScrollPane2-35-50", new Integer(0));
        compMinSize.put("Form", new Dimension(234, 175));
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        prefPaddingInParent.put("Form-jCheckBox2-0-1", new Integer(6)); // parentId-compId-dimension-compAlignment
        compPrefSize.put("jTextField1", new Dimension(59, 20));
        prefPadding.put("jButton1-jScrollPane2-0-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-1", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-2", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-1", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-2", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        compPrefSize.put("jTextField2", new Dimension(59, 20));
        prefPaddingInParent.put("Form-jScrollPane1-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jScrollPane2-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jToggleButton1-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        contInterior.put("Form", new Rectangle(0, 0, 400, 300));
        compBounds.put("jTextField1", new Rectangle(0, 0, 400, 20));
        baselinePosition.put("jTextField1-400-20", new Integer(14));
        compBounds.put("jCheckBox2", new Rectangle(10, 43, 81, 23));
        baselinePosition.put("jCheckBox2-81-23", new Integer(15));
        compBounds.put("jScrollPane1", new Rectangle(10, 78, 35, 86));
        baselinePosition.put("jScrollPane1-35-86", new Integer(0));
        compBounds.put("jButton1", new Rectangle(78, 112, 73, 23));
        baselinePosition.put("jButton1-73-23", new Integer(15));
        compBounds.put("jToggleButton1", new Rectangle(78, 141, 105, 23));
        baselinePosition.put("jToggleButton1-105-23", new Integer(15));
        compBounds.put("jTextField2", new Rectangle(78, 81, 322, 20));
        baselinePosition.put("jTextField2-322-20", new Integer(14));
        compBounds.put("jScrollPane2", new Rectangle(355, 114, 35, 50));
        baselinePosition.put("jScrollPane2-35-50", new Integer(0));
        compMinSize.put("Form", new Dimension(234, 175));
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        prefPaddingInParent.put("Form-jCheckBox2-0-1", new Integer(6)); // parentId-compId-dimension-compAlignment
        compPrefSize.put("jTextField1", new Dimension(59, 20));
        prefPadding.put("jButton1-jScrollPane2-0-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-1", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-2", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jButton1-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-0", new Integer(6)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-1", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-2", new Integer(10)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        prefPadding.put("jToggleButton1-jScrollPane2-0-0-3", new Integer(18)); // comp1Id-comp2Id-dimension-comp2Alignment-paddingType
        compPrefSize.put("jTextField2", new Dimension(59, 20));
        prefPaddingInParent.put("Form-jScrollPane1-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jScrollPane2-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jToggleButton1-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        ld.updateCurrentState();
// < UPDATE CURRENT STATE
    }
}
