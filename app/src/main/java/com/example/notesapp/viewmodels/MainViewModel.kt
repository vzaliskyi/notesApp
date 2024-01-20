package com.example.notesapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.data.idCount
import com.example.notesapp.data.notesList

class MainViewModel: ViewModel() {

    private val notesText: List<Int> = listOf(
        R.string.note1_text,
        R.string.note2_text,
        R.string.note3_text,
        R.string.note4_text,
        R.string.note5_text,
    )


    fun generateStartList(context: Context){
        for(note in notesText){
            idCount += 1
            notesList.add(Note(idCount, context.getString(note), false))
        }
    }
}