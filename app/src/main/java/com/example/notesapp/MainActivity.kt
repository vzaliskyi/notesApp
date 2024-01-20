package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.notesapp.data.Note
import com.example.notesapp.data.idCount
import com.example.notesapp.data.notesList
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.viewmodels.MainViewModel

//import android.content.Context
//import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appToolbar)

        viewModel.generateStartList(this)

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