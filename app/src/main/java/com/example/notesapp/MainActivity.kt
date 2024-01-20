package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.notesapp.data.Note
import com.example.notesapp.data.idCount
import com.example.notesapp.data.notesList
import com.example.notesapp.databinding.ActivityMainBinding
//import android.content.Context
//import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val notesText: List<Int> = listOf(
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
        setSupportActionBar(binding.appToolbar)

        generateStartList()


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

    private fun generateStartList(){
        for(note in notesText){
            idCount += 1
            notesList.add(Note(idCount, getString(note), false))
        }
    }
}