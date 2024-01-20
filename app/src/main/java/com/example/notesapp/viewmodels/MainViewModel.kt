package com.example.notesapp.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.data.idCount
import com.example.notesapp.data.notesList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainViewModel: ViewModel() {

    private val notesText: List<Int> = listOf(
        R.string.note1_text,
        R.string.note2_text,
        R.string.note3_text,
        R.string.note4_text,
        R.string.note5_text,
    )

    val notesListViewModel : Flow<List<Note>> = flow{
        val notesListViewModel = notesList
        emit(notesListViewModel)
        delay(5000)
    }

    fun generateStartList(context: Context){
        for(note in notesText){
            idCount += 1
            notesList.add(Note(idCount, context.getString(note) , false))
        }
    }
}