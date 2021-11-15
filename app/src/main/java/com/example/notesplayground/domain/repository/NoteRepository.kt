package com.example.notesplayground.domain.repository

import com.example.notesplayground.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

     fun getAllNote(): Flow<List<Note>>

     suspend fun getNoteById(id: Int): Note?

     suspend fun insertNote(note: Note )

     suspend fun deleteNote(note: Note )
}