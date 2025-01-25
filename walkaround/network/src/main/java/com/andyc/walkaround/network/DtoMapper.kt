package com.andyc.walkaround.network

import com.andyc.core.domain.venue.Venue
import com.andyc.core.domain.location.Location

fun Item.toVenue(): Venue {
    venue!!.apply {
        return Venue(
            id = venue.id,
            name = venue.name,
            address = venue.address,
            shortDescription = venue.shortDescription,
            imageUrl = image.url,
            location = Location(venue.location[1], venue.location[0]),
            score = venue.rating?.score ?: -1.0
        )
    }
}