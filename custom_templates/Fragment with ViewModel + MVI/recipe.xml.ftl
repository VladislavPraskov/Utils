<?xml version="1.0"?>
<recipe>
    
    <instantiate from="src/app_package/Fragment.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${mainFragmentClass}.kt" />

    <instantiate from="src/app_package/Action.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${action}.kt" />
                   
    <instantiate from="src/app_package/ViewModel.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${viewModelClass}.kt" />
                   
    <instantiate from="src/app_package/ResultAction.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${resultAction}.kt" />
                   
    <instantiate from="src/app_package/ViewState.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${viewState}.kt" />

    <instantiate from="res/layout/fragment.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${mainFragmentLayout}.xml" />
    
    <open file="${escapeXmlAttribute(resOut)}/layout/${mainFragmentLayout}.xml" />
    <open file="${escapeXmlAttribute(srcOut)}/${mainFragmentClass}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${viewModelClass}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${action}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${resultAction}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${viewState}.kt" />
</recipe>
