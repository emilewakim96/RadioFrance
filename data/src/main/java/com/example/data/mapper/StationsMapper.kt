package com.example.data.mapper

import com.example.domain.model.Podcast
import com.example.domain.model.Show
import com.example.domain.model.Station
import com.example.radiofrancetest.android.graphql.ShowsQuery
import com.example.radiofrancetest.android.graphql.StationsQuery

internal class StationsMapper {
    fun mapBrandToStation(brand: StationsQuery.Brand): Station =
        Station(
            id = brand.id,
            title = brand.title,
            description = brand.description
        )

    fun mapToShows(brand: ShowsQuery.Shows?): List<Show>? =
        brand?.edges?.mapNotNull { it?.node }?.map { node -> mapNodeToShow(node) }

    private fun mapNodeToShow(brand: ShowsQuery.Node): Show =
        Show(
            id = brand.id,
            title = brand.title,
            url = brand.url,
            standFirst = brand.standFirst,
            podcast = Podcast(
                rss = brand.podcast?.rss ?: "",
                itunes = brand.podcast?.itunes ?: ""
            )
        )
}