package com.example.notesapp

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.data.Note
import com.example.notesapp.data.notesList
import com.example.notesapp.databinding.ActivityNoteEditBinding

class NoteEditActivity:AppCompatActivity() {

    private lateinit var binding: ActivityNoteEditBinding
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteId = intent.extras!!.getInt("id")
        note = notesList.first { it.id == noteId }
        binding.noteTextView.setText(note.noteText)

        binding.appToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_menu, menu)
        return true
    }
}