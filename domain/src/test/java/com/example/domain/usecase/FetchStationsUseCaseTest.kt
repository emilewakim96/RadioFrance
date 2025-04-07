package com.example.domain.usecase

import com.example.domain.repository.StationsRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test


class FetchStationsUseCaseTest {
    private val stationsRepository: StationsRepository = mockk()
    private val fetchStationsUseCase = FetchStationsUseCase(stationsRepository)

    @Test
    fun `use case calls repository`() = runTest {
        fetchStationsUseCase.run()
        coVerify { stationsRepository.getStations() }
    }

    @Test
    @Throws(Exception::class)
    fun `when repository throws exception return failure result`() = runTest {
        coEvery {
            stationsRepository.getStations()
        } throws (Exception())
        val result = fetchStationsUseCase.run()
        Truth.assertThat(result.isFailure).isTrue()
    }
}
