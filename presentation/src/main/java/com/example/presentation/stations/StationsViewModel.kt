package com.example.presentation.stations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.FetchStationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class StationsViewModel(
    private val fetchStationsUseCase: FetchStationsUseCase,
    private val mapper: StationsMapper
) : ViewModel() {

    private val _state = MutableStateFlow(StationsState())
    val state = _state
        .onStart {
            fetchStationsUseCase.run().onSuccess {
                _state.value = mapper.mapToState(it)
            }.onFailure {
                _state.value = _state.value.copy(
                    error = it
                )
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )
}