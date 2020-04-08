package ${kotlinEscapedPackageName}

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
<#if applicationPackage??>
import ${applicationPackage}.R
</#if>

class ${viewClass} @JvmOverloads constructor(
    context: Context, 
    attrs: AttributeSet? = null, 
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

	var titleText: String? = ""
        set(value) {
            field = value; addressTitle?.text = field
        }

    	var address: String? = ""
        set(value) {
            field = value; addressText?.text = field
        }

    init{
	LayoutInflater.from(context).inflate(R.layout.next_button_text_view, this, true)
        val a = context.obtainStyledAttributes(attrs, R.styleable.${viewClass}, defStyle, 0)
        titleText = a.getString(R.styleable.${viewClass}_titleText)
        address = a.getString(R.styleable.${viewClass}_addressText)

        if (a.hasValue(R.styleable.${viewClass}_exampleDrawable)) {
            val exampleDrawable = a.getResourceId(R.styleable.${viewClass}_icon)
        }
        a.recycle()
    }
}
