package com.example.domain.repository

import com.example.domain.model.Show
import com.example.domain.model.Station

interface StationsRepository {
    suspend fun getStations(): List<Station>
    suspend fun getShows(stationId: String): List<Show>
}