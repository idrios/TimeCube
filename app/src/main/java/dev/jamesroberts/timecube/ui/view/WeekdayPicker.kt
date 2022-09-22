package dev.jamesroberts.timecube.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import dev.jamesroberts.timecube.R

class WeekdayPicker : LinearLayoutCompat {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {}
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs) {}
    constructor(context: Context)
            : super(context) {}
    init {
        inflate(context, R.layout.view_weekday_picker, this)
    }
}