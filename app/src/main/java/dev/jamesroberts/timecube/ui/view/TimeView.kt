package dev.jamesroberts.timecube.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import dev.jamesroberts.timecube.R

class TimeView : LinearLayoutCompat {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {}
    constructor(context: Context) : super(context, null, 0) {}

    override fun onFinishInflate() {
        super.onFinishInflate()
        _hourPicker = findViewById<TimePicker>(R.id.hourpicker)
        _minutePicker = findViewById<TimePicker>(R.id.minutepicker)
        _ampmPicker = findViewById<TimePicker>(R.id.ampmpicker)

    }

    private var _hourPicker : TimePicker? = null;
    private var _minutePicker : TimePicker? = null;
    private var _ampmPicker : TimePicker? = null;

}