package com.example.radiofrance.navigation

import com.example.presentation.shows.STATION_ID_KEY
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Stations: Route

    @Serializable
    data class Shows(
        @SerialName(STATION_ID_KEY) val stationId: String
    ): Route
}
