package com.example.notesplayground.domain.use_cases

import com.example.notesplayground.data.repository.FakeNoteRepository
import com.example.notesplayground.domain.model.Note
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseTest {

    private lateinit var deleteNoteUseCase: DeleteNoteUseCase
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var getAllNotesUseCase: GetAllNotesUseCase
    private lateinit var getNoteUseCase: GetNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository


    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        deleteNoteUseCase = DeleteNoteUseCase(fakeNoteRepository)
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)
        getNoteUseCase = GetNoteUseCase(fakeNoteRepository)
        getAllNotesUseCase = GetAllNotesUseCase(fakeNoteRepository)

    }


    @Test
    fun `deleting a note successful`() = runBlocking {

        val note = Note(1, "title", "content", 12, 2)
        addNoteUseCase.invoke(note = note)
        deleteNoteUseCase.invoke(note)
        val addedNotes = getAllNotesUseCase.invoke().toList()
        assert(addedNotes.flatten().isEmpty())

    }
}