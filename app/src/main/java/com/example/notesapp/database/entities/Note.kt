package com.example.notesapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class Note(
    var text: String,
    var tag: Int? = null,
    var isSelected: Boolean = false,
    val date: LocalDateTime = LocalDateTime.now(),
    @PrimaryKey(autoGenerate = true) val noteId: Int = 0
) {
    val createdDateFormatted: String
        get() = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
}
