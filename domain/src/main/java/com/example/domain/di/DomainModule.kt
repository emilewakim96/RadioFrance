package com.example.domain.di

import com.example.domain.usecase.FetchShowsForStationUseCase
import com.example.domain.usecase.FetchStationsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::FetchStationsUseCase)
    factoryOf(::FetchShowsForStationUseCase)
}