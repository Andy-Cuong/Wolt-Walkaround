package com.andyc.core.domain.venue

import com.andyc.core.domain.location.Location
import com.andyc.core.domain.util.DataError
import com.andyc.core.domain.util.EmptyResult
import com.andyc.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface VenueRepository {
    fun observeCurrentLocation(): Flow<Location>

    suspend fun getNearbyVenues(): Result<List<Venue>, DataError.Network>

    fun getFavoriteVenues(): Flow<List<Venue>>

    suspend fun addVenueAsFavorite(venue: Venue): Result<Venue, DataError.Local>

    suspend fun deleteVenueFromFavorite(venue: Venue)
}