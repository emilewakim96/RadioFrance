package com.example.presentation.stations

import com.example.domain.model.Station
import com.example.presentation.model.StationItem

class StationsMapper(
    private val resources: StationsResources
) {

    fun mapToState(items: List<Station>): StationsState =
        StationsState(
            title = resources.stationsScreenTitle,
            items = items.map {
                StationItem(
                    id = it.id,
                    title = it.title,
                    description = it.description
                )
            },
            isLoading = false
        )

}