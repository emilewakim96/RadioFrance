package com.example.presentation.stations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.FetchStationsUseCase
import com.example.presentation.actions.HomeActionBuilder
import com.example.presentation.actions.HomeExecutor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class StationsViewModel(
    private val fetchStationsUseCase: FetchStationsUseCase,
    private val mapper: StationsMapper
) : ViewModel(),
    HomeExecutor,
    KoinComponent {

    private val actionBuilder: HomeActionBuilder by inject {
        parametersOf(this)
    }

    private val _state = MutableStateFlow(StationsState())
    val state = _state
        .onStart {
            fetchStationsUseCase.run().onSuccess {
                _state.value = mapper.mapToState(it, actionBuilder)
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