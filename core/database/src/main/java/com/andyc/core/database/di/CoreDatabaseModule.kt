package com.andyc.core.database.di

import androidx.room.Room
import com.andyc.core.database.VenueDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            VenueDatabase::class.java,
            "favorite_venue"
        )
            .build()
    }

    single { get<VenueDatabase>().favoriteVenueDao() }
}