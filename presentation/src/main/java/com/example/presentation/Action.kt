package com.example.presentation

open class Action<T>(
    open val text: String = "",
    open val description: String = "",
    open val execute: (T) -> Unit = {}
)
