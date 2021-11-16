package com.example.notesplayground.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesplayground.ui.theme.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
    val time: Long,
    val color: Int
) {
    companion object {

        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
class InvalidNoteException(message: String) : Exception(message)