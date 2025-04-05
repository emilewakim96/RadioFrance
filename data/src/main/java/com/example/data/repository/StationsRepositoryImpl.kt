package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.Show
import com.example.domain.model.Station
import com.example.domain.repository.StationsRepository

internal class StationsRepositoryImpl(
    private val apiService: ApiService
): StationsRepository {

    override suspend fun getStations(): List<Station> {
        return apiService.getStations()
    }

    override suspend fun getShows(stationId: String): List<Show> {
        return apiService.getShows(stationId)
    }
}