package com.andyc.core.data.walkaround

import android.database.sqlite.SQLiteFullException
import com.andyc.core.database.dao.FavoriteVenueDao
import com.andyc.core.database.mappers.toVenue
import com.andyc.core.database.mappers.toVenueEntity
import com.andyc.core.domain.location.Location
import com.andyc.core.domain.util.DataError
import com.andyc.core.domain.util.Result
import com.andyc.core.domain.venue.Venue
import com.andyc.core.domain.venue.VenueDiscoverer
import com.andyc.core.domain.venue.VenueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VenueRepositoryImpl(
    private val venueDiscoverer: VenueDiscoverer,
    private val favoriteVenueDao: FavoriteVenueDao
): VenueRepository {
    override fun observeCurrentLocation(): Flow<Location> =
        venueDiscoverer.observeCurrentLocation()

    override suspend fun getNearbyVenues(): Result<List<Venue>, DataError.Network> =
        venueDiscoverer.getNearbyVenues()

    override fun getFavoriteVenues(): Flow<List<Venue>> {
        return favoriteVenueDao.getFavoriteVenues()
            .map { venueEntity ->
                venueEntity.map { it.toVenue() }
            }
    }

    override suspend fun addVenueAsFavorite(venue: Venue): Result<Venue, DataError.Local> {
        return try {
            favoriteVenueDao.addVenueAsFavorite(venue.toVenueEntity())
            Result.Success(venue)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteVenueFromFavorite(venue: Venue) {
        favoriteVenueDao.deleteVenueFromFavorite(venue.toVenueEntity())
    }
}