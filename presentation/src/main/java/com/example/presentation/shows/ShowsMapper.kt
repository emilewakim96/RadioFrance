package com.example.presentation.shows

import com.example.domain.model.Show
import com.example.presentation.actions.Action
import com.example.presentation.model.ShowItem

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
                        execute = {

                        }
                    )
                )
            }
        )
    }
}