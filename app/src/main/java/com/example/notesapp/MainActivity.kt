package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.coroutineScope
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.viewmodels.MainViewModel
import com.example.notesapp.viewmodels.MainViewModelFactory
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels(){
        MainViewModelFactory(
            NoteDatabase.getInstance(this).noteDao
        )
    }

    private lateinit var notesAdapter: NotesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appToolbar)



        setUpRecyclerView()

        setUpDrawerMenu()

        binding.createButton.setOnClickListener {
            createNewActivity()
        }

    }


    private fun setUpRecyclerView(){
        val action: (Int) -> Unit = {
            goToNoteDetailActivity(it)
        }

        notesAdapter = NotesListAdapter(this, action)
        binding.notesRecyclerView.adapter = notesAdapter

        lifecycle.coroutineScope.launch {
            viewModel.notesList.collect(){
                notesAdapter.submitList(it)
            }
        }


    }

    private fun setUpDrawerMenu(){
        binding.navMenu.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout,binding.appToolbar, R.string.open_nav, R.string.close_nav)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.allNotesItem -> {
                lifecycle.coroutineScope.launch {
                    viewModel.notesList.collect(){
                        notesAdapter.submitList(it)
                    }
                }
            }

            R.id.selectedNotesItem -> {
                lifecycle.coroutineScope.launch {
                    viewModel.selectedNotesList.collect(){
                        notesAdapter.submitList(it)
                    }
                }

            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}