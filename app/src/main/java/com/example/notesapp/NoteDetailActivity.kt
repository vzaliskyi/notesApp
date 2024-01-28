package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.databinding.ActivityNoteDetailBinding
import com.example.notesapp.viewmodels.NoteDetailModelFactory
import com.example.notesapp.viewmodels.NoteDetailViewModel


class NoteDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding
    private lateinit var dialog: AlertDialog

    private var noteId: Int = 0

    private val viewModel: NoteDetailViewModel by viewModels(){
        NoteDetailModelFactory(
            NoteDatabase.getInstance(this).noteDao
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appToolbar)

        noteId = intent.extras!!.getInt("id")

        createDialog()


        viewModel.noteText.observe(this){
            binding.noteTextView.text = it
        }

        binding.noteTextView.setOnClickListener {
            goToNoteEditActivity(noteId)
        }

        binding.appToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    /*I use noteText variable in viewModel to display text on screen.
    Because note text can be updated in NoteEditActivity, I set text
    in method onStart to sure that changes will be display in NoteDetailActivity
    * */
    override fun onStart() {
        super.onStart()
        viewModel.findNote(noteId)
        viewModel.setNoteText()

    }

    private fun goToNoteEditActivity(id: Int){
        val intent = Intent(this, NoteEditActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun createDialog(){
        val builder = AlertDialog.Builder(this)
        builder
            .setMessage("Do you want delete note?")
            .setPositiveButton("Yes"){_, _->
                viewModel.deleteNote()
                finish()
            }
            .setNegativeButton("No"){_, _->

            }

        dialog = builder.create()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.detail_menu, menu)
        menu!!.findItem(R.id.selectItem).setIcon(getSelectIcon())
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deleteItem -> {
                dialog.show()
                return true
            }
            R.id.selectItem -> {
                viewModel.selectNote()

                item.setIcon(getSelectIcon())

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun getSelectIcon(): Int{
        return if (viewModel.isSelected) R.drawable.ic_selected
        else R.drawable.ic_unselected
    }
}