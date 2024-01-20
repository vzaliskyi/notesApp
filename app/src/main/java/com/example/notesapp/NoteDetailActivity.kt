package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.data.Note
import com.example.notesapp.data.notesList
import com.example.notesapp.databinding.ActivityNoteDetailBinding
import com.example.notesapp.viewmodels.NoteDetailViewModel

class NoteDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding


    private val viewModel: NoteDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteId = intent.extras!!.getInt("id")
        viewModel.findNote(noteId)

        binding.noteTextView.text = viewModel.note.noteText

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