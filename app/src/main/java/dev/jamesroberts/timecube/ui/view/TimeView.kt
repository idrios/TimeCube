package dev.jamesroberts.timecube.ui.view

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dev.jamesroberts.timecube.R
import java.util.*

class TimeView : LinearLayoutCompat {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {}
    constructor(context: Context) : super(context, null, 0) {}

    private var _hourPicker : TimePicker? = null;
    private var _minutePicker : TimePicker? = null;
    private var _ampmPicker : TimePicker? = null;

    init {
        inflate(context, R.layout.view_time, this)
        _hourPicker = findViewById<TimePicker>(R.id.hour_picker)
        _minutePicker = findViewById<TimePicker>(R.id.minute_picker)
        _ampmPicker = findViewById<TimePicker>(R.id.ampm_picker)
    }
}