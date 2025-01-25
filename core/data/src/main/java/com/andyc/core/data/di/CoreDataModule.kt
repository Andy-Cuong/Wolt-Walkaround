package com.andyc.core.data.di

import com.andyc.core.data.networking.HttpClientFactory
import com.andyc.core.data.walkaround.VenueRepositoryImpl
import com.andyc.core.domain.venue.VenueRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().getClient()
    }
    singleOf(::VenueRepositoryImpl).bind<VenueRepository>()
}