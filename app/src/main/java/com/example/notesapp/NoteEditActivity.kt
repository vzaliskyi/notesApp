package com.example.notesapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.databinding.ActivityNoteEditBinding
import com.example.notesapp.viewmodels.NoteEditModelFactory
import com.example.notesapp.viewmodels.NoteEditViewModel

class NoteEditActivity:AppCompatActivity() {

    private lateinit var binding: ActivityNoteEditBinding
    private val viewModel: NoteEditViewModel by viewModels(){
        NoteEditModelFactory(
            NoteDatabase.getInstance(this).noteDao
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appToolbar)

        /* I'm trying to get id of passed note
        If i didn't passed id, it's mean that user want create new note
        * */
        try {
            val noteId = intent.extras!!.getInt("id")
            viewModel.findNote(noteId)
        }
        catch(e: NullPointerException){
            viewModel.createNewNote()
        }


        binding.noteEditText.setText(viewModel.note.text)

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
        viewModel.setNewText(newNoteText)
        finish()
    }
}