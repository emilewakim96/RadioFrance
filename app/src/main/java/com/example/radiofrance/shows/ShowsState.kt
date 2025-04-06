package com.example.radiofrance.shows

import com.example.radiofrance.model.ShowItem

data class ShowsState(
    val title: String = "",
    val items: List<ShowItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
