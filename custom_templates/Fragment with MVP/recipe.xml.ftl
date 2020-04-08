<?xml version="1.0"?>
<recipe>
    
    <instantiate from="src/app_package/Fragment.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${mainFragmentClass}.kt" />

    <instantiate from="src/app_package/Presenter.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${presenter}.kt" />
                   
    <instantiate from="src/app_package/PresenterImpl.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${presenterImpl}.kt" />
                   
    <instantiate from="src/app_package/View.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${view}.kt" />
                   

    <instantiate from="res/layout/fragment.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${mainFragmentLayout}.xml" />
    
    <open file="${escapeXmlAttribute(resOut)}/layout/${mainFragmentLayout}.xml" />
    <open file="${escapeXmlAttribute(srcOut)}/${mainFragmentClass}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${presenterImpl}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${view}.kt" />
    <open file="${escapeXmlAttribute(srcOut)}/${presenter}.kt" />
</recipe>
