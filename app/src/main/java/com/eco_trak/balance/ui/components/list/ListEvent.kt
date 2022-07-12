package com.eco_trak.balance.ui.components.list

sealed class ListEvent<T> {
    data class OnNew<T>(val item: T): ListEvent<T>()
    data class OnUpdate<T>(val item: T): ListEvent<T>()
    data class OnDelete<T>(val item: T): ListEvent<T>()
    data class OnError<T>(val exception: Exception) : ListEvent<T>()
}