package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.viewmodels.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var notesAdapter: NotesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appToolbar)



        setUpRecyclerView()

        viewModel.generateStartList(this)

        binding.createButton.setOnClickListener {
            createNewActivity()
        }

    }

    override fun onStart() {
        super.onStart()

        /* List adapter suggest that submitted list should be different each time.
        Variable of list don't contain actually list but address of list in memory.
        So if we call submitList, it compare address of previous list with address of submitted list.
        If addresses are the same, submitList don't call DiffCallback(don't compare lists).
        So that's why i create new list and copy notes lists to get submitList work properly
        * */
        val tempList = viewModel.notesList.toList()

        tempList.forEach {
            Log.d("MainActivity", "Note: $it")
        }

        /* I call submitList in onStart method to make sure that listAdapter will be updated
        when i return from NoteDetailActivity and NoteEditActivity
        * */
        notesAdapter.submitList(tempList)

    }

    private fun setUpRecyclerView(){
        val action: (Int) -> Unit = {
            goToNoteDetailActivity(it)
        }

        notesAdapter = NotesListAdapter(this, action)
        binding.notesRecyclerView.adapter = notesAdapter


    }

    private fun goToNoteDetailActivity(id: Int){
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun createNewActivity(){
        val intent = Intent(this, NoteEditActivity::class.java)
        startActivity(intent)
    }

}