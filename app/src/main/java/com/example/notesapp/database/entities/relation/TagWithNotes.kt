package com.example.notesapp.database.entities.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.notesapp.database.entities.Note
import com.example.notesapp.database.entities.Tag

data class TagWithNotes(
    @Embedded val tag: Tag,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "tag"
    )
    val notes: List<Note>
)
