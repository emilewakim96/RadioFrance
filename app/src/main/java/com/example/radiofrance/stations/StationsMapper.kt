package com.example.radiofrance.stations

import com.example.domain.model.Station
import com.example.radiofrance.actions.HomeActionBuilder
import com.example.presentation.model.StationItem

class StationsMapper(
//    private val resources: StationsResources,
) {

    fun mapToState(items: List<Station>, actionBuilder: HomeActionBuilder): StationsState =
        StationsState(
            title = "Stations",
            items = items.map {
                StationItem(
                    id = it.id,
                    title = it.title,
                    description = it.description
                )
            },
            isLoading = false,
            isError = false
        )

}