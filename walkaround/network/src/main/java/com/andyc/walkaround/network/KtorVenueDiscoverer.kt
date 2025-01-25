@file:OptIn(ExperimentalCoroutinesApi::class)

package com.andyc.walkaround.network

import com.andyc.core.data.networking.get
import com.andyc.core.domain.venue.Venue
import com.andyc.core.domain.venue.VenueDiscoverer
import com.andyc.core.domain.location.Location
import com.andyc.core.domain.util.DataError
import com.andyc.core.domain.util.Result
import com.andyc.core.domain.util.map
import com.andyc.walkaround.domain.LocationObserver
import io.ktor.client.HttpClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class KtorVenueDiscoverer(
    private val locationObserver: LocationObserver,
    private val client: HttpClient
): VenueDiscoverer {

    private var currentLocation = Location(0.0, 0.0)

    override fun observeCurrentLocation(): Flow<Location> {
        return locationObserver
            .observeLocation(intervalMillis = 10_000L)
            .onEach { currentLocation = it }
    }

    override suspend fun getNearbyVenues(): Result<List<Venue>, DataError.Network> {
        return client.get<WoltRestaurantResponse>(
            route = "restaurants",
            queryParameters = mapOf(
                "lat" to currentLocation.lat,
                "lon" to currentLocation.long
            )
        ).map { response ->
            try {
                response.sections[1].items.map { item ->
                    item.toVenue()
                }.take(15)
            } catch (e: IndexOutOfBoundsException) {
                emptyList()
            } catch (e: NullPointerException) {
                emptyList()
            }
        }
    }
}