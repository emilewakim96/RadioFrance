package com.example.presentation.model

import com.example.presentation.actions.Action

data class ShowItem(
    val id: String,
    val title: String,
    val url: String?,
    val standFirst: String?,
    val link: Action<String>,
)