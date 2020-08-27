package com.example.notekeeper

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.app.ActivityCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val logcatTag = this::class.simpleName
    private var notePosition = POSITION_NOT_SET

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // needed to trigger onPrepareOptionsMenu
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        // Populate course drop down list
        val adapterCourseInfo = ArrayAdapter(
            context?.applicationContext!!,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )
        adapterCourseInfo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourses.adapter = adapterCourseInfo

        // retrieve notePosition from savedInstanceState if exist (after rotate screen), or from intent (after redirect)
        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET)
            ?: activity?.intent!!.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)
        if (notePosition != POSITION_NOT_SET)
            displayNote()   // display existing note
        else {
            DataManager.notes.add(NoteInfo())   // add an empty new note
            notePosition = DataManager.notes.lastIndex
        }

        // Update fragment on menu select
        (activity as MainActivity?)!!.setFragmentRefreshListener(object :
            FragmentRefreshListener {
            override fun onFragmentNext() {
                displayNextNote()
            }
            override fun onFragmentPrev() {
                displayPrevNote()
            }
        })
        Log.d(logcatTag, "onViewCreated")
    }

    // called on activity destroyed (rotate screen)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(NOTE_POSITION, notePosition)
    }

    private fun displayNote() {
        if (notePosition > DataManager.notes.lastIndex){
            Log.e(logcatTag, "invalid notePosition: $notePosition, lastIndex: ${DataManager.notes.lastIndex}")
        }
        Log.i(logcatTag, "notePosition: $notePosition")

        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
//        val CoursePosition2 = (spinnerCourses.adapter as ArrayAdapter<CourseInfo>).getPosition(note.course)
        spinnerCourses.setSelection(coursePosition)
    }

    public fun displayPrevNote() {
        notePosition = (notePosition - 1) //% spinnerCourses.count
        displayNote()
        // trigger onPrepareOptionsMenu
        activity?.invalidateOptionsMenu()
    }

    public fun displayNextNote() {
        notePosition = (notePosition + 1) //% spinnerCourses.count
        displayNote()
        // trigger onPrepareOptionsMenu
        activity?.invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        if (notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu.findItem(R.id.action_next)
            menuItem.icon =
                getDrawable(context?.applicationContext!!, R.drawable.ic_baseline_block_24)
            menuItem.isEnabled = false
        }

        if (notePosition == 0) {
            val menuPrevItem = menu.findItem(R.id.action_prev)
            menuPrevItem.isVisible = false
        }

        super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        SaveNote()
        Log.d(logcatTag, "onPause")
    }

    private fun SaveNote() {
        val note = DataManager.notes[notePosition]
        note.title = textNoteTitle.text.toString()
        note.text = textNoteText.text.toString()
        note.course = spinnerCourses.selectedItem as CourseInfo
    }

}