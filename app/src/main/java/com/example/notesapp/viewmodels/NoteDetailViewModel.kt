package com.example.notesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.NOTES_LIST
import com.example.notesapp.data.Note

class NoteDetailViewModel:ViewModel() {

    /* Live data should be declared during initialization of viewModel
    * That's why I assign random note to _note variable
    * Than in onCreate method in NoteDetailActivity I get noteId
    * And invoke method assignNoteToMutableLiveDate to assign correct data
    * */
    var note: Note = NOTES_LIST[0]

    private var _noteText: MutableLiveData<String> = MutableLiveData("")
    private var _isSelected: Boolean = false

    val noteText: LiveData<String> = _noteText
    val isSelected: Boolean get() = _isSelected



    private fun findNote(id: Int): Note{
        val currentNote = NOTES_LIST.first { it.id == id }
        _isSelected = currentNote.isSelected
        return currentNote
    }

    fun assignNoteToMutableLiveData(id:Int){
        note = findNote(id)
    }

    fun setNoteText(){
        _noteText.postValue(note.noteText)
    }

    fun deleteNote(){
        NOTES_LIST.remove(note)
    }

    fun selectNote(){
        _isSelected = !_isSelected
        NOTES_LIST.first{it == note}.isSelected = isSelected
    }

}