package com.example.notesapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.ID_COUNT
import com.example.notesapp.data.MenuItems
import com.example.notesapp.data.Note
import com.example.notesapp.data.NOTES_LIST
import com.example.notesapp.data.START_NOTE_TEXT

class MainViewModel: ViewModel() {

    val notesList : MutableList<Note> get() = NOTES_LIST
    var selectedMenuItem:MenuItems = MenuItems.ALL
    fun generateStartList(context: Context){
        for(note in START_NOTE_TEXT){
            ID_COUNT += 1
            NOTES_LIST.add(Note(ID_COUNT, context.getString(note) , false))
        }
    }

    fun filterNotesBySelected():List<Note> = notesList.filter { it.isSelected }


}