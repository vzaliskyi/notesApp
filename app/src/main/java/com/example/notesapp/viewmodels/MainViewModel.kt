package com.example.notesapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.data.ID_COUNT
import com.example.notesapp.data.MenuItems
import com.example.notesapp.data.Note
import com.example.notesapp.data.NOTES_LIST
import com.example.notesapp.data.START_NOTE_TEXT
import com.example.notesapp.database.NoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class MainViewModel(private val dao: NoteDao): ViewModel() {

    val notesList : Flow<List<com.example.notesapp.database.entities.Note>> by lazy{
        dao.getAllNotes()
    }
    var selectedMenuItem:MenuItems = MenuItems.ALL
    fun generateStartList(context: Context){
        for(note in START_NOTE_TEXT){
            ID_COUNT += 1
            NOTES_LIST.add(Note(ID_COUNT, context.getString(note) , false))
        }
    }

    fun filterNotesBySelected():List<Note> = NOTES_LIST.filter { it.isSelected }

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