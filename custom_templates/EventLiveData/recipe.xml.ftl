<?xml version="1.0"?>
<recipe>
    <instantiate from="src/app_package/Event.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${event}.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${event}.kt" />
</recipe>
