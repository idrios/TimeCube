package dev.jamesroberts.timecube.ui.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
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

    private var _currentTime: String = "0"
    private var _times: Array<String> = arrayOf<String>()

    init {
        inflate(context, R.layout.view_time_picker, this);
        val recyclerView : RecyclerView = findViewById(R.id.time_picker_recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(context)

    }

    /** /////////////////////////////////////////////////////////////////////////////////////// */
    /** ///////////////////// RecyclerView complementary classes ////////////////////////////// */
    /** /////////////////////////////////////////////////////////////////////////////////////// */

    /**
     * Explanation of RecyclerView:
     *  There's lots of documentation of how to code a RecyclerView but few actually explain
     *  what's happening, which makes those tutorials feel like confusing copy/paste.
     *
     *  From a user perspective, the RecyclerView is like a ScrollView, except that ScrollViews
     *  have an upper and lower limit while the RecyclerView repeats from the beginning.
     *
     *  From a technical perspective, the difference is actually that the ScrollView loads all of
     *  the contents into the app as soon as it's created while the RecyclerView is adding them
     *  dynamically.
     *
     *  The fact that it's loading each element dynamically makes RecyclerView a good choice for
     *  rendering very large datasets, such as a chat conversation. And of course our use case,
     *  which is just displaying numbers 1-12 for hour, 0-59 for minute, and AM / PM.
     *
     *  We need a custom view for each number (as a String because this might be displaying
     *  "AM/PM"), a ViewHolder to wrap our custom view, and an Adapter to link the ViewHolders
     *  together in a way that makes sense for our app.
     */

    /**
     * TimeUnit
     *  Data model associated with our custom view
     *  Note this does not have the view itself, only the data. The adapter is the where
     *  we bind the data to the view.
     */
    data class TimeUnit(val data : String) {}

    /**
     * ViewHolder
     *  Just a wrapper around the view that we want to be recyclable. Need it because the Adapter
     *  needs some of methods that come with RecyclerView.ViewHolder. It does also give some
     *  freedom for using logic to decide what kind of view should be rendered (e.g. if this
     *  were a texting app, we could decide to render as an incoming or outgoing message here)
     *  but our app is not that complicated.
     */
    class ViewHolder(timeUnitView: View) : RecyclerView.ViewHolder(timeUnitView) {
        val textView: TextView = itemView.findViewById(R.id.time_picker_elem_text)
    }

    /**
     * Adapter
     *  Connects the views together with our own custom logic interspersed. Creates new
     *  ViewHolders dynamically when they're near the visible portion of the RecyclerView.
     *  After a ViewHolder is created, it gets attached to the list of ViewHolders in a process
     *  called "Binding". This Binding is not to be confused with ViewBinding or DataBinding.
     */
    class Adapter(private val _list: List<TimeUnit>) : RecyclerView.Adapter<ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_time_unit, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val timeUnit = _list[position] // get data from ViewHolder
            holder.textView.text = timeUnit.data
        }

        override fun getItemCount(): Int {
            return _list.size
        }
    }
}