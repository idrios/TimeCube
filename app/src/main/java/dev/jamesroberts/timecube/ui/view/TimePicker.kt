package dev.jamesroberts.timecube.ui.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ScrollView
import androidx.recyclerview.widget.RecyclerView
import dev.jamesroberts.timecube.R

class TimePicker : FrameLayout {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
    }

    constructor(context: Context, attrs: AttributeSet?) :
            super(context, attrs) {
    }

    constructor(context: Context) :
            super(context) {
    }

    init {
        inflate(context, R.layout.view_time_picker, this);
    }

    private var _currentTime: String = "0"
    private var _recyclerView: RecyclerView? = null

}

private class TimeCollection(var data: List<String>) : ArrayList<TimePickerElement>() {
    init{
        for(elem : String in data){
            add(TimePickerElement(elem))
        }
    }
}

/*private class TimeCollectionAdapter(private val timeList: List<TimeElementModel>) : RecyclerView.Adapter<TimeCollectionAdapter.ViewHolder>{

    companion object ViewHolder(itemView:View) : RecyclerView.ViewHolder(){
        init{

        }
    }

}

interface TimePickerAdapter : RecyclerView.Adapter<>

 */