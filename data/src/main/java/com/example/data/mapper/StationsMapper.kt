package com.example.data.mapper

import com.example.domain.model.Show
import com.example.domain.model.Station
import com.example.radiofrancetest.android.graphql.ShowsQuery
import com.example.radiofrancetest.android.graphql.StationsQuery.Brand

internal class StationsMapper {

    fun mapBrandsToStations(brands: List<Brand?>?): List<Station> =
        brands?.filterNotNull()?.map { brand -> mapBrandToStation(brand) } ?: emptyList()

    private fun mapBrandToStation(brand: Brand): Station =
        Station(
            id = brand.id,
            title = brand.title,
            description = brand.description
        )

    fun mapToShows(brand: ShowsQuery.Shows?): List<Show> =
        brand?.edges?.mapNotNull { it?.node }?.map { node -> mapNodeToShow(node) } ?: emptyList()

    private fun mapNodeToShow(brand: ShowsQuery.Node): Show =
        Show(
            id = brand.id,
            title = brand.title,
            url = brand.url,
            standFirst = brand.standFirst
        )
}