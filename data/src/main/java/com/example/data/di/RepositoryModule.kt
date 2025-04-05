package com.example.data.di

import com.example.data.mapper.StationsMapper
import com.example.data.repository.StationsRepositoryImpl
import com.example.domain.repository.StationsRepository
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::StationsRepositoryImpl) { createdAtStart() } bind StationsRepository::class

    factoryOf(::StationsMapper)
}