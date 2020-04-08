<?xml version="1.0"?>
<recipe>

    <instantiate from="src/app_package/Activity.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${mainActivityClass}.kt" />

    <instantiate from="src/app_package/Action.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${action}.kt" />
                   
    <instantiate from="src/app_package/ViewModel.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${viewModelClass}.kt" />
                   
    <instantiate from="src/app_package/ResultAction.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${resultAction}.kt" />
                   
    <instantiate from="src/app_package/ViewState.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${viewState}.kt" />

    <instantiate from="res/layout/activity.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${mainActivityLayout}.xml" />
    
    <open file="${escapeXmlAttribute(resOut)}/layout/${mainActivityLayout}.xml" />
    <open file="${escapeXmlAttribute(srcOut)}/${mainActivityClass}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${viewModelClass}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${action}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${resultAction}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${viewState}.kt" />
</recipe>
