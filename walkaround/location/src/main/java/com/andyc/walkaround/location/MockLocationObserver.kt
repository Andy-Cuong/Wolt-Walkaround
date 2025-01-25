package com.andyc.walkaround.location

import com.andyc.core.domain.location.Location
import com.andyc.walkaround.domain.LocationObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockLocationObserver: LocationObserver {
    override fun observeLocation(intervalMillis: Long): Flow<Location> {
        return flow {
            var counter = 0

            while (true) {
                emit(locations.getModulo(counter))
                delay(intervalMillis)
                counter++
            }
        }
    }

    companion object {
        private val locations = listOf(
            Location(60.169418, 24.931618),
            Location(60.169818, 24.932906),
            Location(60.170005, 24.935105),
            Location(60.169108, 24.936210),
            Location(60.168355, 24.934869),
            Location(60.167560, 24.932562),
            Location(60.168254, 24.931532),
            Location(60.169012, 24.930341),
            Location(60.170085, 24.929569),
        )
    }
}

private fun <T> List<T>.getModulo(index: Int): T {
    return this[index % size]
}