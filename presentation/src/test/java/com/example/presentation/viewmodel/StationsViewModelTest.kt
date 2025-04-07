package com.example.presentation.viewmodel

import com.example.domain.usecase.FetchStationsUseCase
import com.example.presentation.stations.StationsMapper
import com.example.presentation.stations.StationsState
import com.example.presentation.stations.StationsViewModel
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
class StationsViewModelTest: AutoCloseKoinTest() {
    private lateinit var viewModel: StationsViewModel

    private val fetchStationsUseCase: FetchStationsUseCase = mockk()
    private val mapper: StationsMapper = mockk {
        every { mapToState(any()) } returns StationsState()
    }
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        startKoin {
            module {
                factory { fetchStationsUseCase }
            }
        }
        viewModel = StationsViewModel(fetchStationsUseCase, mapper)
    }

    @Test
    fun `initial state isLoading true`() {
        val state = viewModel.state
        Truth.assertThat(state.value.isLoading).isTrue()
    }

    @Test
    fun `initial state isError false`() {
        val state = viewModel.state
        Truth.assertThat(state.value.isError).isFalse()
    }

    @Test
    fun `when getItemsUseCase throws error state isError true`() = runTest {
        coEvery { fetchStationsUseCase.run() } returns Result.failure(Throwable("api error"))
        val state: List<StationsState> = viewModel.state.take(2).toList()
        Truth.assertThat(state.last().isError).isTrue()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}