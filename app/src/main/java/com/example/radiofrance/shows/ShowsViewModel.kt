package com.example.radiofrance.shows

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.usecase.FetchShowsForStationUseCase
import com.example.radiofrance.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ShowsViewModel(
    private val fetchShowsForStationUseCase: FetchShowsForStationUseCase,
    private val mapper: ShowsMapper,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(ShowsState())
    private val stationId = savedStateHandle.toRoute<Route.Shows>().stationId

    val state = _state
        .onStart {
            fetchShowsForStationUseCase.run(stationId).onSuccess {
                _state.value = mapper.mapToState(it, stationId, /*actionBuilder*/)
                println("EMILE state ${_state.value}")
            }.onFailure {
                _state.value = _state.value.copy(
                    isError = true
                )
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )
}