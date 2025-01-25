package com.andyc.walkaround.presentation.venue_discovery.mapper

import com.andyc.core.domain.venue.Venue
import com.andyc.walkaround.presentation.venue_discovery.model.VenueUi

fun Venue.toVenueUi(): VenueUi {
    return VenueUi(
        id = id,
        name = name,
        address = address,
        shortDescription = shortDescription,
        imageUrl = imageUrl,
        location = location,
        score = if (score >= 0) "$score/10" else "No score"
    )
}

fun VenueUi.toVenue(): Venue {
    val score = try {
        score.substring(0, score.indexOf('/')).toDouble()
    } catch (e: NumberFormatException) {
        -1.0
    }

    return Venue(
        id = id,
        name = name,
        address = address,
        shortDescription = shortDescription,
        imageUrl = imageUrl,
        location = location,
        score = score
    )
}