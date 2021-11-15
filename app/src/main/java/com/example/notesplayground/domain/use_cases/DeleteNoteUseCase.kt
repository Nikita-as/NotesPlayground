package com.example.notesplayground.domain.use_cases

import com.example.notesplayground.domain.model.Note
import com.example.notesplayground.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}