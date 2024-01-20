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
    * Refactor: remove duplicate invoke of method findNote*/
    var _note: Note = findNote(1)

    private var _noteText: MutableLiveData<String> = MutableLiveData("")
    private var _isSelected: MutableLiveData<Boolean> = MutableLiveData(false)

    val noteText: LiveData<String> = _noteText
    val isSelected: LiveData<Boolean> get() = _isSelected



    private fun findNote(id: Int): Note{
        return NOTES_LIST.first { it.id == id }
    }

    fun assignNoteToMutableLiveData(id:Int){
        _note = findNote(id)
    }

    fun setNoteText(){
        _noteText.postValue(_note.noteText)
    }

}