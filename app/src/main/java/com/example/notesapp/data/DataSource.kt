package com.example.notesapp.data

import com.example.notesapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

var ID_COUNT = 0

var NOTES_LIST: MutableList<Note> = mutableListOf()

val START_NOTE_TEXT: List<Int> = listOf(
    R.string.note1_text,
    R.string.note2_text,
    R.string.note3_text,
    R.string.note4_text,
    R.string.note5_text,
)