package com.example.radiofrance.shows

import com.example.domain.model.Show
import com.example.radiofrance.actions.Action
import com.example.radiofrance.model.ShowItem

class ShowsMapper {
    fun mapToState(shows: List<Show>, stationId: String): ShowsState {
        return ShowsState(
            title = "Shows for $stationId",
            isLoading = false,
            isError = false,
            items = shows.map { show ->
                ShowItem(
                    id = show.id,
                    title = show.title,
                    url = show.url,
                    standFirst = show.standFirst,
                    link = Action(
                        text = show.podcast.itunes,
                        execute = {} // TODO
                    )
                )
            }
        )
    }
}