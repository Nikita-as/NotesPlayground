package com.example.notesplayground.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}