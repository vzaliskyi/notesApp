package com.example.notesapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        setSupportActionBar(binding.appToolbar)

        val noteId = intent.extras!!.getInt("id")
        note = notesList.first { it.id == noteId }
        binding.noteEditText.setText(note.noteText)

        binding.appToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.saveItem -> {
                saveNote()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun saveNote(){
        val newNoteText = binding.noteEditText.text.toString()
        note.noteText = newNoteText
        finish()
    }
}