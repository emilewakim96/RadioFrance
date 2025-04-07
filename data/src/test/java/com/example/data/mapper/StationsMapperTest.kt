package com.example.data.mapper

import com.example.domain.model.Show
import com.example.domain.model.Station
import com.example.radiofrancetest.android.graphql.ShowsQuery
import com.example.radiofrancetest.android.graphql.StationsQuery
import com.google.common.truth.Truth
import org.junit.Test

class StationsMapperTest {

    private val stationsMapper = StationsMapper()

    @Test
    fun `mapToStations return emptyList when shows from api is null`() {
        val result = stationsMapper.mapBrandsToStations(null)
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `mapToShows return emptyList when shows from api is null`() {
        val result = stationsMapper.mapToShows(null)
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `mapToStations filter out item when null brand`() {
        val expectedResult = listOf(
            Station(
                id = "23",
                title = "Station Title",
                description = "Station Description"
            ),
            Station(
                id = "24",
                title = "Station Title 2",
                description = "Station Description 2",
            ),
        )
        val stations = mockStationsQueryResponse()
        Truth.assertThat(stationsMapper.mapBrandsToStations(stations.brands)).isEqualTo(expectedResult)
    }

    @Test
    fun `mapToShows filter out item when null edge or null node`() {
        val expectedResult = listOf(
            Show(
                id = "134",
                title = "Show Title",
                url = "Show Url",
                standFirst = "Show StandFirst"
            ),
            Show(
                id = "135",
                title = "Show Title 2",
                url = "Show Url 2",
                standFirst = "Show StandFirst 2"
            ),
        )
        val shows = mockShowsQueryResponse()
        Truth.assertThat(stationsMapper.mapToShows(shows)).isEqualTo(expectedResult)
    }

    private fun mockStationsQueryResponse() = StationsQuery.Data(
        brands = listOf(
            StationsQuery.Brand(
                id = "23",
                title = "Station Title",
                description = "Station Description"
            ),
            null,
            StationsQuery.Brand(
                id = "24",
                title = "Station Title 2",
                description = "Station Description 2",
            ),
            null
        )
    )

    private fun mockShowsQueryResponse() = ShowsQuery.Shows(
        listOf(
            ShowsQuery.Edge(
                node = ShowsQuery.Node(
                    id = "134",
                    title = "Show Title",
                    url = "Show Url",
                    standFirst = "Show StandFirst"
                ), cursor = "23"
            ),
            null,
            ShowsQuery.Edge(
                node = ShowsQuery.Node(
                    id = "135",
                    title = "Show Title 2",
                    url = "Show Url 2",
                    standFirst = "Show StandFirst 2"
                ), cursor = "24"
            ),
            null,
            ShowsQuery.Edge(
                node = null,
                cursor = "25"
            ),
        )
    )
}