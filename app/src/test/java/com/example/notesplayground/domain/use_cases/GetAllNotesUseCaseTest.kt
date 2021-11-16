package com.example.notesplayground.domain.use_cases

import com.example.notesplayground.data.repository.FakeNoteRepository
import com.example.notesplayground.domain.model.Note
import com.example.notesplayground.domain.util.NoteOrder
import com.example.notesplayground.domain.util.OrderType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllNotesUseCaseTest {

    private lateinit var getAllNotesUseCase: GetAllNotesUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getAllNotesUseCase = GetAllNotesUseCase(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    time = index.toLong(),
                    color = index

                )
            )
        }
        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach { fakeNoteRepository.insertNote(it) }
        }
    }

    @Test
    fun `Order notes by title ascending, correct order`() = runBlocking {
        val notes = getAllNotesUseCase(NoteOrder.Title(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }

    @Test
    fun `Order notes by title descending, correct order`() = runBlocking {
        val notes = getAllNotesUseCase(NoteOrder.Title(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isGreaterThan(notes[i + 1].title)
        }
    }

    @Test
    fun `Order notes by data ascending, correct order`() = runBlocking {
        val notes = getAllNotesUseCase(NoteOrder.Data(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].time).isLessThan(notes[i + 1].time)
        }
    }

    @Test
    fun `Order notes by data descending, correct order`() = runBlocking {
        val notes = getAllNotesUseCase(NoteOrder.Data(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].time).isGreaterThan(notes[i + 1].time)
        }
    }
    @Test
    fun `Order notes by color ascending, correct order`() = runBlocking {
        val notes = getAllNotesUseCase(NoteOrder.Color(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isLessThan(notes[i + 1].color)
        }
    }

    @Test
    fun `Order notes by color descending, correct order`() = runBlocking {
        val notes = getAllNotesUseCase(NoteOrder.Color(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isGreaterThan(notes[i + 1].color)
        }
    }

}