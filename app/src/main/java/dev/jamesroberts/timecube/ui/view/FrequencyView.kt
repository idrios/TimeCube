package dev.jamesroberts.timecube.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import dev.jamesroberts.timecube.R

class FrequencyView : LinearLayoutCompat {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {}
    constructor(context: Context) : super(context, null, 0) {}
    init {
        inflate(context, R.layout.view_frequency, this)

    }
}