package dev.jamesroberts.timecube.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.jamesroberts.timecube.R
import dev.jamesroberts.timecube.data.model.Alarm

/**
 * Fragments get destroyed and created by various lifecycle events that we likely don't
 * have control over, and when it gets recreated it will call the no-argument constructor.
 * We want this fragment to accept arguments so we use this this factory method of
 * implementation ([AlarmEditorFragment.newInstance]) that retrieves the arguments from
 * savedInstanceState.
 */
private const val ARG_ALARM = "alarm"

class AlarmEditorFragment : Fragment() {
    private var alarmStringDELETE_THIS_IMPLEMENT_BETTER: String? = null
    private var alarm: Alarm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            alarmStringDELETE_THIS_IMPLEMENT_BETTER = it.getString(ARG_ALARM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm_editor, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param alarm The Alarm Model used to load the current values in the Alarm Editor Fragment.
         * @return A new instance of fragment AlarmEditorFragment.
         */
        @JvmStatic
        fun newInstance(alarm: String) =
            AlarmEditorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ALARM, alarmStringDELETE_THIS_IMPLEMENT_BETTER)
                }
            }
    }


}