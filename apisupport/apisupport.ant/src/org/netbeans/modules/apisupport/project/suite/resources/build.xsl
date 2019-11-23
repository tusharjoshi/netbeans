<?xml version="1.0" encoding="UTF-8"?>
<!--

    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

-->
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:project="http://www.netbeans.org/ns/project/1"
                xmlns:sproject="http://www.netbeans.org/ns/nb-module-suite-project/1"
                xmlns:xalan="http://xml.apache.org/xslt"
                exclude-result-prefixes="xalan project sproject">
    <xsl:output method="xml" indent="yes" encoding="UTF-8" xalan:indent-amount="4"/>
    <xsl:template match="/">
        <xsl:comment> You may freely edit this file. See harness/README in the NetBeans platform </xsl:comment>
        <xsl:comment> for some information on what you could do (e.g. targets to override). </xsl:comment>
        <xsl:comment> If you delete this file and reopen the project it will be recreated. </xsl:comment>
        <xsl:variable name="name" select="/project:project/project:configuration/sproject:data/sproject:name"/>
        <project name="{$name}"><!-- XXX consider sanitizing as per PropertyUtils.getUsablePropertyName -->
            <!-- XXX default attr? -->
            <xsl:attribute name="basedir">.</xsl:attribute>
            <description>Builds the module suite <xsl:value-of select="$name"/>.</description>
            <import file="nbproject/build-impl.xml"/>
        </project>
    </xsl:template>
</xsl:stylesheet>
