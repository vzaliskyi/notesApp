package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notesapp.databinding.ActivityMainBinding
//import android.content.Context
//import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val notesList: List<Int> = listOf(
        R.string.note1_text,
        R.string.note2_text,
        R.string.note3_text,
        R.string.note4_text,
        R.string.note5_text,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val action: (Int) -> Unit = {
            goToNoteDetailActivity(it)
        }

        val notesAdapter = NotesListAdapter(this, action)
        binding.notesRecyclerView.adapter = notesAdapter
        notesAdapter.submitList(notesList)
    }

    private fun goToNoteDetailActivity(id: Int){
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}