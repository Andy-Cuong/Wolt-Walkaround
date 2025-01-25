package com.andyc.core.domain.venue

import com.andyc.core.domain.location.Location

data class Venue(
    val id: String,
    val name: String,
    val address: String,
    val shortDescription: String,
    val imageUrl: String,
    val location: Location,
    val score: Double
)