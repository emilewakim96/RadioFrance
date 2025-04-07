package com.example.presentation.mapper

import com.example.domain.model.Station
import com.example.presentation.model.StationItem
import com.example.presentation.stations.StationsMapper
import com.example.presentation.stations.StationsResources
import com.example.presentation.stations.StationsState
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import org.junit.Test


class StationsMapperTest {

    private val resources: StationsResources = mockk {
        every { stationsScreenTitle } returns "Stations"
    }
    private val mapper = StationsMapper(resources)

    @Test
    fun `mapToStations return return StationsState`() {
        val stations = createStationsList()
        Truth.assertThat(mapper.mapToState(stations)).isEqualTo(
            StationsState(
                title = "Stations",
                isLoading = false,
                error = null,
                items = createStationItemsList()
            )
        )
    }

    private fun createStationItemsList() = listOf(
        StationItem(
            id = "23",
            title = "Station Title",
            description = "Station Description"
        ),
        StationItem(
            id = "24",
            title = "Station Title 2",
            description = "Station Description 2",
        )
    )

    private fun createStationsList() = listOf(
        Station(
            id = "23",
            title = "Station Title",
            description = "Station Description"
        ),
        Station(
            id = "24",
            title = "Station Title 2",
            description = "Station Description 2",
        )
    )
}