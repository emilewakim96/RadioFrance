package com.example.presentation.shows

import com.example.domain.model.Show
import com.example.presentation.model.ShowItem

class ShowsMapper {
    fun mapToState(shows: List<Show>, stationId: String, actionBuilder: ShowsActionBuilder): ShowsState {
        return ShowsState(
            title = "Shows for $stationId",
            isLoading = false,
            isError = false,
            items = shows.map { show ->
                ShowItem(
                    id = show.id,
                    title = show.title,
                    url = "Clicker pour afficher l'url",
                    standFirst = show.standFirst,
                    seeUrl = actionBuilder.seeUrl(id = show.id, url = show.url.orEmpty())
                )
            }
        )
    }
}