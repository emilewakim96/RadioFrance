package com.example.data.api

import com.example.domain.model.Show
import com.example.domain.model.Station

interface ApiService {

    suspend fun getStations(): List<Station>

    suspend fun getShows(stationId: String): List<Show>
}