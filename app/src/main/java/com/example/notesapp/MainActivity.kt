package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notesapp.databinding.ActivityMainBinding
//import android.content.Context
//import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notesList: List<String> = listOf(
            getString(R.string.note1_text),
            getString(R.string.note2_text),
            getString(R.string.note3_text),
            getString(R.string.note4_text),
            getString(R.string.note5_text),
        )

        //Log.d("MainActivity", "List: $notesList")

        val notesAdapter = NotesListAdapter()
        binding.notesRecyclerView.adapter = notesAdapter

        notesAdapter.submitList(notesList)
    }
}