package com.example.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.domain.usecase.FetchShowsForStationUseCase
import com.example.presentation.shows.ShowsActionBuilder
import com.example.presentation.shows.ShowsMapper
import com.example.presentation.shows.ShowsState
import com.example.presentation.shows.ShowsViewModel
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

@OptIn(ExperimentalCoroutinesApi::class)
class ShowsViewModelTest: AutoCloseKoinTest() {
    private lateinit var viewModel: ShowsViewModel

    private val fetchShowsForStationUseCase: FetchShowsForStationUseCase = mockk()
    private val actionBuilder: ShowsActionBuilder = mockk {
        every { seeUrl(any(), any()) } returns mockk()
    }
    private val mapper: ShowsMapper = mockk {
        every { mapToState(any(), any(), any()) } returns ShowsState()
    }
    private val savedStateHandle: SavedStateHandle = mockk {
        every { get<String>("stationId") } returns stationId
    }

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        startKoin {
            module {
                factory { fetchShowsForStationUseCase }
                factory { actionBuilder }
            }
        }
        viewModel = ShowsViewModel(fetchShowsForStationUseCase, mapper, savedStateHandle)
    }

    @Test
    fun `initial state isLoading true`() {
        val state = viewModel.state
        Truth.assertThat(state.value.isLoading).isTrue()
    }

    @Test
    fun `initial state error is null`() {
        val state = viewModel.state
        Truth.assertThat(state.value.error).isNull()
    }

    @Test
    fun `when getItemsUseCase throws error state error is not null`() = runTest {
        coEvery { fetchShowsForStationUseCase.run(any()) } returns Result.failure(Throwable("api error"))
        val state: List<ShowsState> = viewModel.state.take(2).toList()
        Truth.assertThat(state.last().error).isNotNull()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}

private const val stationId = "franceInfo"
