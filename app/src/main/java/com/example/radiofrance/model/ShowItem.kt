package com.example.radiofrance.model

import com.example.radiofrance.actions.Action

data class ShowItem(
    val id: String,
    val title: String,
    val url: String?,
    val standFirst: String?,
    val link: Action<String>,
)