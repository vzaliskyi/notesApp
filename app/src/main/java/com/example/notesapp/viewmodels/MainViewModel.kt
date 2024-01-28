package com.example.notesapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.database.NoteDao
import com.example.notesapp.database.entities.Note
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val dao: NoteDao): ViewModel() {

    val notesList : Flow<List<Note>> by lazy{
        dao.getAllNotes()
    }

    val selectedNotesList: Flow<List<Note>> by lazy {
        dao.getSelectedNotes(true)
    }

}

class MainViewModelFactory(private val dao: NoteDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}