package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.coroutineScope
import com.example.notesapp.data.MenuItems
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.database.entities.Note
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.viewmodels.MainViewModel
import com.example.notesapp.viewmodels.MainViewModelFactory
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
//    , NavigationView.OnNavigationItemSelectedListener
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

        val dao = NoteDatabase.getInstance(this).noteDao

//        lifecycle.coroutineScope.launch {
//            dao.insertNote(Note("Hello"))
//        }


        setUpRecyclerView()

        //setUpDrawerMenu()

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
//        if(viewModel.selectedMenuItem == MenuItems.ALL){
//            val tempList = viewModel.notesList.toList()
//            notesAdapter.submitList(tempList)
//        }
//        else{
//            notesAdapter.submitList(viewModel.filterNotesBySelected())
//        }


        /* I call submitList in onStart method to make sure that listAdapter will be updated
        when i return from NoteDetailActivity and NoteEditActivity
        * */


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

//    private fun setUpDrawerMenu(){
//        binding.navMenu.setNavigationItemSelectedListener(this)
//
//        val toggle = ActionBarDrawerToggle(
//            this, binding.drawerLayout,binding.appToolbar, R.string.open_nav, R.string.close_nav)
//
//        binding.drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//    }

    private fun goToNoteDetailActivity(id: Int){
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun createNewActivity(){
        val intent = Intent(this, NoteEditActivity::class.java)
        startActivity(intent)
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.allNotesItem -> {
//                val tempList = viewModel.notesList.toList()
//                notesAdapter.submitList(tempList)
//                viewModel.selectedMenuItem = MenuItems.ALL
//            }
//            R.id.selectedNotesItem -> {
//                notesAdapter.submitList(viewModel.filterNotesBySelected())
//                viewModel.selectedMenuItem = MenuItems.SELECT
//            }
//        }
//        binding.drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }

}