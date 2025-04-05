package com.example.domain.model

data class Show(
    val id: String,
    val title: String,
    val url: String?,
    val standFirst: String?,
    val podcast: Podcast,
)