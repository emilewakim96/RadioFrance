package com.example.presentation.mapper

import com.example.domain.model.Show
import com.example.domain.model.Station
import com.example.presentation.Action
import com.example.presentation.model.ShowItem
import com.example.presentation.model.StationItem
import com.example.presentation.shows.ShowsActionBuilder
import com.example.presentation.shows.ShowsMapper
import com.example.presentation.shows.ShowsState
import com.example.presentation.stations.StationsMapper
import com.example.presentation.stations.StationsState
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import org.junit.Test


class ShowsMapperTest {

    private val mapper = ShowsMapper()
    private val actionBuilder = mockk<ShowsActionBuilder> {
        every { seeUrl(any(), any()) } returns seeUrlAction
    }

    @Test
    fun `mapToShows return return ShowsState`() {
        val shows = createShowsList()
        Truth.assertThat(mapper.mapToState(shows, "franceInfo", actionBuilder)).isEqualTo(
            ShowsState(
                title = "Shows for franceInfo",
                isLoading = false,
                isError = false,
                items = createShowItemsList()
            )
        )
    }

    private fun createShowItemsList() = listOf(
        ShowItem(
            id = "23",
            title = "Show Title",
            standFirst = "Show StandFirst",
            url = "Clicker pour afficher l'url",
            seeUrl = seeUrlAction
        ),
        ShowItem(
            id = "24",
            title = "Show Title 2",
            standFirst = "Show StandFirst 2",
            url = "Clicker pour afficher l'url",
            seeUrl = seeUrlAction
        )
    )

    private fun createShowsList() = listOf(
        Show(
            id = "23",
            title = "Show Title",
            url = "Show Url",
            standFirst = "Show StandFirst"
        ),
        Show(
            id = "24",
            title = "Show Title 2",
            url = "Show Url 2",
            standFirst = "Show StandFirst 2",
        )
    )
}

private val seeUrlAction = Action<String>(
    text = "Show Url",
    execute = { }
)