package com.example.notesplayground.domain.use_cases

import com.example.notesplayground.domain.model.Note
import com.example.notesplayground.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}