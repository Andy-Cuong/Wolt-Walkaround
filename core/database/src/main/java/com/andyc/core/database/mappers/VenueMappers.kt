package com.andyc.core.database.mappers

import com.andyc.core.database.entity.VenueEntity
import com.andyc.core.domain.location.Location
import com.andyc.core.domain.venue.Venue

fun VenueEntity.toVenue(): Venue {
    return Venue(
        id = id,
        name = name,
        address = address,
        shortDescription = shortDescription,
        imageUrl = imageUrl,
        location = Location(latitude, longitude),
        score = score
    )
}

fun Venue.toVenueEntity(): VenueEntity {
    return VenueEntity(
        id = id,
        name = name,
        address = address,
        shortDescription = shortDescription,
        imageUrl = imageUrl,
        latitude = location.lat,
        longitude = location.long,
        score = score
    )
}