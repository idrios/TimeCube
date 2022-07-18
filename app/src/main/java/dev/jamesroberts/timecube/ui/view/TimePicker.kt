package dev.jamesroberts.timecube.ui.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.jamesroberts.timecube.R

// TODO: Include possible times in attribute set
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

    private val models : List<TimeUnit> = listOf(
        TimeUnit("1"),
        TimeUnit("2"),
        TimeUnit("3"),
        TimeUnit("4"),
        TimeUnit("5"),
    )
    private var _recyclerView : RecyclerView
    private var _currentTime: String
    private var _adapter: RecyclerView.Adapter<ViewHolder>

    init {
        inflate(context, R.layout.view_time_picker, this)
        _recyclerView = findViewById(R.id.time_picker_recycler_view)
        _currentTime = "1"
        _adapter = InfiniteAdapter(models, 3)
        _recyclerView.adapter = _adapter
        _recyclerView.layoutManager = LinearLayoutManager(context)
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
     *
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

    /**
     * InfiniteAdapter
     *  Having successfully implemented the adapter, it only allows you to scroll from one end
     *  of the list to the next. I want the adapter to repeat infinitely. The implementation
     *  to accomplish this will be to duplicate the list at both ends. When the view crosses
     *  one list into the next, then the list adapter continues reading from that next list,
     *  the list that is 2-away from the current list gets destroyed, and a new list gets created
     *  that is adjacent to the now-current list.
     *  |           ||            ||            ||  _________ ||            |
     *  |           ||            ||     [ ]    ||     [0]    ||  _________ |
     *  | _________ ||            ||     [ ]    ||     [1]    ||     [0]    |
     *  |    [0]    ||  _________ ||  ___[ ]___ ||  ___[2]___ ||     [1]    |
     *  |    [1]    ||     [0]    ||     [0]    ||     [0]    ||  ___[2]___ |
     *  | ___[2]___ ||     [1]    ||     [0]    ||     [1]    ||     [0]    |
     *  |  ->[0]    ||  _->[2]___ ||  _->[0]___ ||  _->[2]___ ||   ->[1]    |
     *  |    [1]    ||     [0]    ||     [0]    ||     [0]    ||  ___[2]___ |
     *  | ___[2]___ ||     [1]    ||     [0]    ||     [1]    ||     [0]    |
     *  |    [0]    ||  ___[2]___ ||  ___[0]___ ||  ___[2]___ ||     [1]    |
     *  |    [1]    ||     [0]    ||     [X]    ||            ||  ___[2]___ |
     *  | ___[2]___ ||     [1]    ||     [X]    ||            ||            |
     *  |           ||  ___[2]___ ||  ___[X]___ ||            ||            |
     *  Hah. Just kidding. This was clever but would screw up on fling with the adapter
     *  so we just make the adapter colossally long and start in the middle instead.
     */
    class InfiniteAdapter(private val _list: List<TimeUnit>, private val startOffset: Int) : RecyclerView.Adapter<ViewHolder>(){

        private val START_POS = (Int.MAX_VALUE / 2) + (startOffset % _list.size)
        private var layoutManager: RecyclerView.LayoutManager? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_time_unit, parent, false)
            return ViewHolder(view)
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            layoutManager = recyclerView.layoutManager
            layoutManager?.scrollToPosition(START_POS)
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            super.onDetachedFromRecyclerView(recyclerView)
            layoutManager = null
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var realPosition = position
            if(position < 100 || position > Int.MAX_VALUE - 100){
                realPosition += START_POS
                layoutManager?.scrollToPosition(realPosition)
            }
            val timeUnit = _list[realPosition % _list.size] // get data from ViewHolder
            holder.textView.text = timeUnit.data
        }

        override fun getItemCount(): Int {
            return Int.MAX_VALUE
        }
    }

    /**
     * InfiniteLayoutManager
     *  A normal RecyclerView wouldn't need this, but a normal RecyclerView doesn't allow
     *  you to scroll infinitely in both directions. Override some default behaviors here.
     *
     */
    class InfiniteLayoutManager() : RecyclerView.LayoutManager() {
        override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
            TODO("Not yet implemented")
        }
    }
}