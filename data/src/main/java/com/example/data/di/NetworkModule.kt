package com.example.data.di

import com.apollographql.apollo.ApolloClient
import com.example.data.api.ApiService
import com.example.data.api.ApolloStationsClient
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::provideApolloClient) bind ApolloClient::class
    singleOf(::ApolloStationsClient) { createdAtStart() } bind ApiService::class
}

private fun provideApolloClient(): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .addHttpHeader("X-Token", TOKEN)
        .build()
}

const val BASE_URL = "https://openapi.radiofrance.fr/v1/graphql/"
const val TOKEN = "84c107b0-22f0-4958-883d-381edaa54174"
