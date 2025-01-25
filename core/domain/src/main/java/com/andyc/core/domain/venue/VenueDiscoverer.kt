package com.andyc.core.domain.venue

import com.andyc.core.domain.location.Location
import com.andyc.core.domain.util.DataError
import com.andyc.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface VenueDiscoverer {
    fun observeCurrentLocation(): Flow<Location>

    suspend fun getNearbyVenues(): Result<List<Venue>, DataError.Network>
}