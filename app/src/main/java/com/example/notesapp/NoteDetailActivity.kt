package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.data.Note
import com.example.notesapp.data.notesList
import com.example.notesapp.databinding.ActivityNoteDetailBinding

class NoteDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding

    private lateinit var note:Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteId = intent.extras!!.getInt("id")
        note = notesList.filter { it.id == noteId }.first()
        binding.noteTextView.text = note.noteText

        binding.noteTextView.setOnClickListener {
            goToNoteEditActivity(noteId)
        }

        binding.appToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun goToNoteEditActivity(id: Int){
        val intent = Intent(this, NoteEditActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}