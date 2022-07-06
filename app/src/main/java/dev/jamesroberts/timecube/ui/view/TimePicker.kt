package dev.jamesroberts.timecube.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class TimePicker : FrameLayout {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes){}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr){}
    constructor(context: Context, attrs: AttributeSet?) :
            super(context, attrs){}
    constructor(context: Context) :
            super(context){}
}