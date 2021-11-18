package com.example.notesplayground.domain.use_cases

import com.example.notesplayground.data.repository.FakeNoteRepository
import com.example.notesplayground.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNoteUseCaseTest {

    private lateinit var getNoteUseCase: GetNoteUseCase
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository


    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getNoteUseCase = GetNoteUseCase(fakeNoteRepository)
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)
    }

    @Test
    fun `getting the note successfully`() = runBlocking {
        val note = Note(1, "title", "content", 12, 2)
        addNoteUseCase.invoke(note = note)
        val receivedNote = getNoteUseCase.invoke(note.id ?: return@runBlocking)
        if (receivedNote != null) {
            assert(receivedNote == note)
        }
    }
}