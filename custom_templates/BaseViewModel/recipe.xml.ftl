<?xml version="1.0"?>
<recipe>
    <instantiate from="src/app_package/BaseViewModel.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${baseViewModel}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${baseViewModel}.kt" />
</recipe>
