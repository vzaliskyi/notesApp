package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.coroutineScope
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.viewmodels.MainViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appToolbar)

        viewModel.generateStartList(this)

        setUpRecyclerView()



    }

    private fun setUpRecyclerView(){
        val action: (Int) -> Unit = {
            goToNoteDetailActivity(it)
        }

        val notesAdapter = NotesListAdapter(this, action)
        binding.notesRecyclerView.adapter = notesAdapter
        //notesAdapter.submitList(notesList)
        lifecycle.coroutineScope.launch {
            viewModel.notesListViewModel.collect(){
                notesAdapter.submitList(it)
            }
        }
    }

    private fun goToNoteDetailActivity(id: Int){
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}