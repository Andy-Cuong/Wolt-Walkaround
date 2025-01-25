package com.andyc.walkaround.presentation.venue_discovery.model

import com.andyc.core.domain.location.Location

data class VenueUi(
    val id: String,
    val name: String,
    val address: String,
    val shortDescription: String,
    val imageUrl: String,
    val location: Location,
    val score: String
)