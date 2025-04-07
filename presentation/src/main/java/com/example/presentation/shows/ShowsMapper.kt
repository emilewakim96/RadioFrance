package com.example.presentation.shows

import com.example.domain.model.Show
import com.example.presentation.model.ShowItem
import com.example.presentation.stations.StationsResources

class ShowsMapper(
    private val resources: StationsResources
) {
    fun mapToState(shows: List<Show>, stationId: String, actionBuilder: ShowsActionBuilder): ShowsState {
        return ShowsState(
            title = resources.getShowsForStationScreenTitle(stationId),
            isLoading = false,
            items = shows.map { show ->
                ShowItem(
                    id = show.id,
                    title = show.title,
                    url = resources.clickForUrlText,
                    standFirst = show.standFirst,
                    seeUrl = actionBuilder.seeUrl(id = show.id, url = show.url.orEmpty())
                )
            }
        )
    }
}