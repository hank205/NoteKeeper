package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Redirect to add new note
        fab.setOnClickListener { view ->
            val addNoteIntent = Intent(this, MainActivity::class.java)
            startActivity(addNoteIntent)
        }

        // Populate Note list
        ListViewNoteList.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)

        // Redirect to edit note
        ListViewNoteList.setOnItemClickListener { parent, view, position, id ->
            val editNoteIntent = Intent(this, MainActivity::class.java)
            editNoteIntent.putExtra(NOTE_POSITION, position)
            startActivity(editNoteIntent)
        }

    }

    override fun onResume() {
        super.onResume()
        (ListViewNoteList.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}