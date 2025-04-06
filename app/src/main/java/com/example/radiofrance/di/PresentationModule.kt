package com.example.radiofrance.di


import com.example.radiofrance.actions.HomeActionBuilder
import com.example.radiofrance.shows.ShowsMapper
import com.example.radiofrance.shows.ShowsViewModel
import com.example.radiofrance.stations.StationsMapper
import com.example.radiofrance.stations.StationsResources
import com.example.radiofrance.stations.StationsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    factoryOf(::StationsMapper)
    factoryOf(::ShowsMapper)
    viewModelOf(::StationsViewModel)
    viewModelOf(::ShowsViewModel)

    factory { parametersHolder -> HomeActionBuilder(parametersHolder.get()) }
//    factory { StationsResources }
}