package com.example.presentation.shows

import com.example.presentation.model.ShowItem

data class ShowsState(
    val title: String = "",
    val items: List<ShowItem> = emptyList(),
    val isLoading: Boolean = true,
    val error: Throwable? = null
)
