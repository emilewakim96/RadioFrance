package com.example.radiofrance.di

import com.example.presentation.stations.StationsResources
import com.example.radiofrance.StationsResourcesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule  = module {
    factory {
        StationsResourcesImpl(androidContext())
    } bind StationsResources::class
}