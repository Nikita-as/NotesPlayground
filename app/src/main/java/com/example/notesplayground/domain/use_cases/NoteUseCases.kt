package com.example.notesplayground.domain.use_cases

data class NoteUseCases(
    val getAllNotesUseCase: GetAllNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
) {
}