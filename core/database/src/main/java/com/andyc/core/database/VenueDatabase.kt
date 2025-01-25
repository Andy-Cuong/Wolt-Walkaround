package com.andyc.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andyc.core.database.dao.FavoriteVenueDao
import com.andyc.core.database.entity.VenueEntity

@Database(entities = [VenueEntity::class], version = 1)
abstract class VenueDatabase: RoomDatabase() {
    abstract fun favoriteVenueDao(): FavoriteVenueDao
}