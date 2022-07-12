package dev.jamesroberts.timecube.ui.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.LinearLayoutCompat
import dev.jamesroberts.timecube.R

class TimeView : LinearLayoutCompat {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {}
    constructor(context: Context) : super(context, null, 0) {}

    init {
        inflate(context, R.layout.view_time, this)
        _hourPicker = findViewById<TimePicker>(R.id.hour_picker)
        _minutePicker = findViewById<TimePicker>(R.id.minute_picker)
        _ampmPicker = findViewById<TimePicker>(R.id.ampm_picker)
        
    }

    private var _hourPicker : TimePicker? = null;
    private var _minutePicker : TimePicker? = null;
    private var _ampmPicker : TimePicker? = null;

}