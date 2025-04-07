package com.example.presentation.stations

interface StationsResources {
    val stationsScreenTitle: String
    val clickForUrlText: String
    fun getShowsForStationScreenTitle(stationId: String): String
}