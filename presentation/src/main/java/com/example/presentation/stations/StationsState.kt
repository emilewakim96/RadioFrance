package com.example.presentation.stations

import com.example.presentation.model.StationItem


data class StationsState(
    val title: String = "",
    val items: List<StationItem> = emptyList(),
    val isLoading: Boolean = true,
    val error: Throwable? = null,
)
