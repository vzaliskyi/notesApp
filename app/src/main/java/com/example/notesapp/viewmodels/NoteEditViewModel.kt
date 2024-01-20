package com.example.notesapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.notesapp.data.Note
import com.example.notesapp.data.notesList

class NoteEditViewModel: ViewModel() {
    private lateinit var _note: Note

    val note get() = _note

    fun findNote(id: Int){
        _note = notesList.filter { it.id == id }.first()
    }

    fun setNewText(newText: String){
        _note.noteText = newText
    }
}