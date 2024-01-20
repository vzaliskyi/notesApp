package com.example.notesapp

import android.os.Bundle
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

        binding.appToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}