package com.example.presentation.shows

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.FetchShowsForStationUseCase
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
    private val stationId = savedStateHandle.get<String>("stationId").orEmpty()

    val state = _state
        .onStart {
            fetchShowsForStationUseCase.run(stationId).onSuccess {
                _state.value = mapper.mapToState(it, stationId, /*actionBuilder*/)
                println("EMILE stationId ${stationId}")
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