package com.example.domain.usecase

import com.example.domain.model.Show
import com.example.domain.model.Station
import com.example.domain.repository.StationsRepository

class FetchShowsForStationUseCase(
    private val stationsRepository: StationsRepository
) {
    suspend fun run(stationId: String): Result<List<Show>> = runCatching {
        stationsRepository.getShows(stationId)
    }
}