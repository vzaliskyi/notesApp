package com.example.notesapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.database.NoteDao
import com.example.notesapp.database.entities.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NoteDetailViewModel(private val dao: NoteDao):ViewModel() {


    private lateinit var note: Note

    private var _noteText: MutableLiveData<String> = MutableLiveData("")
    private var _isSelected: Boolean = false

    val noteText: LiveData<String> get() = _noteText
    val isSelected: Boolean get() = _isSelected



    fun findNote(id: Int){
        runBlocking {
            launch {
                note = dao.getNote(id)
                _isSelected = note.isSelected
                _noteText.value = note.text
            }

        }
    }


    fun setNoteText(){

        _noteText.value = note.text
        Log.d("MainActivity", "setNoteText was called. ${_noteText.value}")
    }

    fun deleteNote(){
        GlobalScope.launch {
            dao.deleteNote(note)
        }
    }

    fun selectNote(){
        _isSelected = !_isSelected
        note.isSelected = _isSelected
        GlobalScope.launch {
            dao.insertNote(note)
        }
    }

}

class NoteDetailModelFactory(private val dao: NoteDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteDetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}