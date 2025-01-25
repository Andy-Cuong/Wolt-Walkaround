package com.andyc.woltwalkaround

import android.app.Application
import com.andyc.core.data.di.coreDataModule
import com.andyc.core.database.di.coreDatabaseModule
import com.andyc.walkaround.di.walkaroundLocationModule
import com.andyc.walkaround.di.walkaroundNetworkModule
import com.andyc.walkaround.presentation.venue_discovery.di.walkaroundPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WoltWalkaroundApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WoltWalkaroundApp)
            modules(
                coreDataModule,
                walkaroundNetworkModule,
                walkaroundLocationModule,
                walkaroundPresentationModule,
                coreDatabaseModule
            )
        }
    }
}