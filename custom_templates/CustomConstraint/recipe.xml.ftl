<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>
<recipe>
    <@kt.addAllKotlinDependencies />
    <merge from="root/res/values/attrs.xml.ftl"
             to="${escapeXmlAttribute(resOut)}/values/attrs_${view_class}.xml" />
    <instantiate from="root/res/layout/sample.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${view_class}.xml" />

    <instantiate from="root/src/app_package/CustomConstraint.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${viewClass}.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${viewClass}.kt" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${view_class}.xml" />
</recipe>
