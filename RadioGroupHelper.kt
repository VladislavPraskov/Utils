package com.android.mooncalendar.presentation.custon_view

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout


class RadioGroupHelper : ConstraintHelper {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val radioViews = ArrayList<RadioButton>()

    private val childCheckChangeListener =
        CompoundButton.OnCheckedChangeListener { radioButton, isChecked ->
            if (isChecked.not()) return@OnCheckedChangeListener
            for (view in radioViews) {
                view.isChecked = (view.id == radioButton.id) && isChecked
            }
        }

    override fun init(attrs: AttributeSet?) {
        super.init(attrs)
        this.mUseViewMeasure = false
    }

    override fun updatePreLayout(container: ConstraintLayout) {
        for (i in 0 until this.mCount) {
            val id = this.mIds[i]
            val view = container.getViewById(id)
            if (view != null && view is RadioButton) {
                radioViews.add(view)
                view.setOnCheckedChangeListener(childCheckChangeListener)
            }
        }
    }

    override fun updatePostLayout(container: ConstraintLayout?) {
        val params = this.layoutParams as ConstraintLayout.LayoutParams
        params.width = 0
        params.height = 0
    }


}