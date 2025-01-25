package com.andyc.walkaround.di

import com.andyc.core.domain.venue.VenueDiscoverer
import com.andyc.walkaround.network.KtorVenueDiscoverer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val walkaroundNetworkModule = module {
    singleOf(::KtorVenueDiscoverer).bind<VenueDiscoverer>()
}