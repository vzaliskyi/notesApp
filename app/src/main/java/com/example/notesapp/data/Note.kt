package com.example.notesapp.data

import androidx.lifecycle.MutableLiveData


data class Note (
    val id:Int,
    var noteText: String,
    var isSelected: Boolean
        )