package com.andyc.walkaround.presentation.venue_discovery

import com.andyc.core.domain.location.Location
import com.andyc.core.domain.venue.Venue
import com.andyc.walkaround.presentation.venue_discovery.model.VenueUi

data class VenueDiscoveryState(
    val currentLocation: Location = Location(0.0, 0.0),
    val nearbyVenues: List<VenueUi> = emptyList(),
    val favoriteVenues: List<Venue> = emptyList()
)
