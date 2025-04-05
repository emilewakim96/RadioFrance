package com.example.radiofrance.shows

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.FetchShowsForStationUseCase

class ShowsViewModel(
    private val fetchShowsForStationUseCase: FetchShowsForStationUseCase
): ViewModel() {

//    suspend fun fetchShowsForStation(stationId: String) {
//        fetchShowsForStationUseCase.run(stationId)
//    }
}