package com.example.notesapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.database.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE isSelected = :isSelected")
    fun getSelectedNotes(isSelected: Boolean): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE noteId = :id")
    suspend fun getNote(id: Int): Note

    @Delete
    fun deleteNote(note: Note)
}
