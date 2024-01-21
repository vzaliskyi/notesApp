package com.example.notesapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.notesapp.data.ID_COUNT
import com.example.notesapp.data.NOTES_LIST
import com.example.notesapp.data.Note

class NoteEditViewModel: ViewModel() {
    private lateinit var _note: Note
    private var isNewNote: Boolean = false

    val note get() = _note

    fun findNote(id: Int){
        _note = NOTES_LIST.first { it.id == id }
    }

    fun setNewText(newText: String){
        _note.noteText = newText
        if (isNewNote) saveNewNote()
    }

    fun createNewNote(){
        ID_COUNT += 1
        _note = Note(ID_COUNT, "", false)
        isNewNote = true

    }

    private fun saveNewNote(){
        NOTES_LIST.add(_note)
    }
}