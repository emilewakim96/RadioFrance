package com.example.radiofrance.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Stations: Route

    @Serializable
    data class Shows(
        val stationId: String
    ): Route
}