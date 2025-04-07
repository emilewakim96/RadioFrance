package com.example.presentation.stations

import com.example.presentation.model.StationItem


data class StationsState(
    val title: String = "",
    val items: List<StationItem> = emptyList(),
    val isError: Boolean = false,
    val isLoading: Boolean = true
)
