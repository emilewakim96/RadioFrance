package com.example.domain.usecase

import com.example.domain.repository.StationsRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test


class FetchShowsForStationUseCaseTest {
    private val stationsRepository: StationsRepository = mockk()
    private val fetchShowsForStationUseCase = FetchShowsForStationUseCase(stationsRepository)

    @Test
    fun `use case calls repository`() = runTest {
        val stationId = "franceinfo"
        fetchShowsForStationUseCase.run(stationId)
        coVerify { stationsRepository.getShows(stationId) }
    }

    @Test
    @Throws(Exception::class)
    fun `when repository throws exception return failure result`() = runTest {
        coEvery {
            stationsRepository.getShows(any())
        } throws (Exception())
        val result = fetchShowsForStationUseCase.run("francebleue")
        Truth.assertThat(result.isFailure).isTrue()
    }
}