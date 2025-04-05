package com.example.data.api

import com.apollographql.apollo.ApolloClient
import com.example.data.mapper.StationsMapper
import com.example.domain.model.Show
import com.example.domain.model.Station
import com.example.radiofrancetest.android.graphql.ShowsQuery
import com.example.radiofrancetest.android.graphql.StationsQuery
import com.example.radiofrancetest.android.graphql.type.StationsEnum

internal class ApolloStationsClient(
    private val apolloClient: ApolloClient,
    private val stationsMapper: StationsMapper
) : ApiService {

    override suspend fun getStations(): List<Station> {
        return apolloClient.query(StationsQuery()).execute()
            .data?.brands?.filterNotNull()?.map {
                stationsMapper.mapBrandToStation(it)
            } ?: emptyList()
    }

    override suspend fun getShows(stationId: String): List<Show> {
        return apolloClient.query(ShowsQuery(StationsEnum.valueOf(stationId))).execute()
            .data?.shows?.let {
                stationsMapper.mapToShows(it)
            } ?: emptyList()
    }
}