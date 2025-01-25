package com.andyc.walkaround.presentation.venue_discovery.di

import com.andyc.walkaround.presentation.venue_discovery.VenueDiscoveryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val walkaroundPresentationModule = module {
    viewModelOf(::VenueDiscoveryViewModel)
}