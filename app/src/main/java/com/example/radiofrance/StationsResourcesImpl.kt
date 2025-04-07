package com.example.radiofrance

import android.content.Context
import com.example.presentation.stations.StationsResources

class StationsResourcesImpl(
    private val context: Context
): StationsResources {

    override val stationsScreenTitle: String =
        context.getString(R.string.stations_screen_title)

    override fun getShowsForStationScreenTitle(stationId: String): String =
        context.getString(R.string.shows_screen_title, stationId)

    override val clickForUrlText: String =
        context.getString(R.string.click_for_url)
}