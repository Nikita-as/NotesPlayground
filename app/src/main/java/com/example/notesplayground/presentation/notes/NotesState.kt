package com.example.notesplayground.presentation.notes

import com.example.notesplayground.domain.model.Note
import com.example.notesplayground.domain.util.NoteOrder
import com.example.notesplayground.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Data(orderType = OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
) {
}