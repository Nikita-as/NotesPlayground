package com.example.notesplayground.domain.use_cases

import com.example.notesplayground.data.repository.FakeNoteRepository
import com.example.notesplayground.domain.model.InvalidNoteException
import com.example.notesplayground.domain.model.Note
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var getNoteUseCase: GetNoteUseCase
    private lateinit var getAllNotesUseCase: GetAllNotesUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {

        fakeNoteRepository = FakeNoteRepository()
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)
        getNoteUseCase = GetNoteUseCase(fakeNoteRepository)
        getAllNotesUseCase = GetAllNotesUseCase(fakeNoteRepository)
    }


    @Test
    fun `adding a note successful`() = runBlocking {

        val note = Note(1, "title", "content", 10, 3)

        addNoteUseCase.invoke(note)

        val newNote = getAllNotesUseCase.invoke().toList()
        assert(newNote.flatten().contains(note))

    }

    @Test(expected = InvalidNoteException::class)
    fun `check that the title is empty`() = runBlocking {

        val note = Note(1, " ", "content", 10, 3)

            addNoteUseCase.invoke(note)
            Assert.fail()

    }

    @Test(expected = InvalidNoteException::class)
    fun `check that the content is empty`() = runBlocking {

        val note = Note(1, "title", " ", 11, 3)

        addNoteUseCase.invoke(note)
        Assert.fail()

    }
}