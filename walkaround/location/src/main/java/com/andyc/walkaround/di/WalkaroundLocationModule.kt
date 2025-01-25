package com.andyc.walkaround.di

import com.andyc.walkaround.domain.LocationObserver
import com.andyc.walkaround.location.MockLocationObserver
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val walkaroundLocationModule = module {
    singleOf(::MockLocationObserver).bind<LocationObserver>()
}