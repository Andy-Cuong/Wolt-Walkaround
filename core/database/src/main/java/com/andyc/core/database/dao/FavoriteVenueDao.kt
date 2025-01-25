package com.andyc.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.andyc.core.database.entity.VenueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteVenueDao {
    @Query("SELECT * FROM VenueEntity")
    fun getFavoriteVenues(): Flow<List<VenueEntity>>

    @Upsert
    suspend fun addVenueAsFavorite(venue: VenueEntity)

    @Delete
    suspend fun deleteVenueFromFavorite(venue: VenueEntity)
}