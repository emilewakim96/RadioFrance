package com.example.presentation.di

import com.example.presentation.actions.HomeActionBuilder
import com.example.presentation.shows.ShowsMapper
import com.example.presentation.shows.ShowsViewModel
import com.example.presentation.stations.StationsMapper
import com.example.presentation.stations.StationsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::StationsViewModel)
    factoryOf(::StationsMapper)
    factoryOf(::ShowsMapper)
    viewModelOf(::ShowsViewModel)

    factory { parametersHolder -> HomeActionBuilder(parametersHolder.get()) }
}