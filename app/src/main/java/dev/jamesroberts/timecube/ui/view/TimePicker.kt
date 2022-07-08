package dev.jamesroberts.timecube.ui.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ScrollView
import androidx.recyclerview.widget.RecyclerView
import dev.jamesroberts.timecube.R

class TimePicker : FrameLayout {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes){}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr){}
    constructor(context: Context, attrs: AttributeSet?) :
            super(context, attrs){}
    constructor(context: Context) :
            super(context){}

    init{
        inflate(context, R.layout.view_time_picker, this);
    }

    private var _currentTime : String = "0"

}