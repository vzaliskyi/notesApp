package com.example.notesapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.notesapp.data.ID_COUNT
import com.example.notesapp.data.NOTES_LIST
import com.example.notesapp.data.Note

class NoteEditViewModel: ViewModel() {
    private lateinit var _note: Note

    val note get() = _note

    fun findNote(id: Int){
        _note = NOTES_LIST.first { it.id == id }
    }

    fun setNewText(newText: String){
        _note.noteText = newText
    }

    fun createNewNote(){
        ID_COUNT += 1
        val newNote = Note(ID_COUNT, "", false)
        NOTES_LIST.add(newNote)

        findNote(ID_COUNT)
    }
}