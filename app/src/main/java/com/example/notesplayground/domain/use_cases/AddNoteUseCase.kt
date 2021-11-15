package com.example.notesplayground.domain.use_cases

import com.example.notesplayground.domain.model.InvalidNoteException
import com.example.notesplayground.domain.model.Note
import com.example.notesplayground.domain.repository.NoteRepository

class AddNoteUseCase(
    private val noteRepository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Название не может быть пустым")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Заметка не может быть пустой")
        }
        noteRepository.insertNote(note)
    }
}