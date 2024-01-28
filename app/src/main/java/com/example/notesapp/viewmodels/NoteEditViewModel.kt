package com.example.notesapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.database.NoteDao
import com.example.notesapp.database.entities.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NoteEditViewModel(private val dao: NoteDao): ViewModel() {
    private lateinit var _note: Note

    val note get() = _note

    fun findNote(id: Int){
        runBlocking {
            launch {
                _note = dao.getNote(id)
            }

        }
    }

    fun setNewText(newText: String){
        _note.text = newText
        saveNewNote()
    }

    fun createNewNote(){
        _note = Note( "")
    }

    private fun saveNewNote(){
        GlobalScope.launch {
            Log.d("MainActivity", "$_note")
            dao.insertNote(_note)
        }

    }
}

class NoteEditModelFactory(private val dao: NoteDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteEditViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteEditViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}