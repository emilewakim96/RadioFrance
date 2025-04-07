package com.example.presentation.shows

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.FetchShowsForStationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class ShowsViewModel(
    private val fetchShowsForStationUseCase: FetchShowsForStationUseCase,
    private val mapper: ShowsMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel(),
    ShowsExecutor,
    KoinComponent {

    private val actionBuilder: ShowsActionBuilder by inject {
        parametersOf(this)
    }

    private val _state = MutableStateFlow(ShowsState())
    private val stationId = savedStateHandle.get<String>("stationId").orEmpty()

    val state = _state
        .onStart {
            fetchShowsForStationUseCase.run(stationId).onSuccess {
                _state.value = mapper.mapToState(it, stationId, actionBuilder)
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

    override fun seeShowUrl(id: String, url: String) {
        _state.value = _state.value.copy(
            items = _state.value.items.map { item ->
                if (item.id == id) item.copy(url = url) else item
            }
        )
    }
}