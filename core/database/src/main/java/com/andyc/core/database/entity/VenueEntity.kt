package com.andyc.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VenueEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val address: String,
    val shortDescription: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val score: Double
)
