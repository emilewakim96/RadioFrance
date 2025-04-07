package com.example.domain.usecase

import com.example.domain.model.Station
import com.example.domain.repository.StationsRepository

class FetchStationsUseCase(
    private val stationsRepository: StationsRepository
) {
    suspend fun run(): Result<List<Station>> = runCatching {
        stationsRepository.getStations()
    }
}