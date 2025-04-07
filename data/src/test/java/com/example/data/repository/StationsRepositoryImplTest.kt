package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.Show
import com.example.domain.model.Station
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test


class StationsRepositoryImplTest {

    private val apiService: ApiService = mockk()
    private val stationsRepository = StationsRepositoryImpl(apiService)

    @Test
    fun `getStations returns list of stations`() = runTest {
        val expectedResult = createMockStationsList()
        coEvery { apiService.getStations() } returns expectedResult
        Truth.assertThat(stationsRepository.getStations()).isEqualTo(expectedResult)
    }

    @Test
    fun `getShowsForStation returns list of shows`() = runTest {
        val expectedResult = createMockShowsList()
        coEvery { apiService.getShows(any()) } returns createMockShowsList()
        Truth.assertThat(stationsRepository.getShows("franceinfo")).isEqualTo(expectedResult)
    }

    private fun createMockStationsList() = listOf(
        Station(
            id = "franceinfo",
            title = "France Info",
            description = "Description"
        ),
        Station(
            id = "franceculture",
            title = "France Culture",
            description = "Description"
        ),
        Station(
            id = "france inter",
            title = "France Inter",
            description = "Description"
        )
    )

    private fun createMockShowsList() = listOf(
        Show(
            id = "283",
            title = "first show ",
            url = "url1",
            standFirst = "standFirst"
        ),
        Show(
            id = "284",
            title = "second show ",
            url = "url2",
            standFirst = "standFirst"
        ),
        Show(
            id = "285",
            title = "third show ",
            url = "url3",
            standFirst = "standFirst"
        )
    )
}
