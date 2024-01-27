package com.example.notesapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    val name: String,
    @PrimaryKey val tagId: Int = 0
)
