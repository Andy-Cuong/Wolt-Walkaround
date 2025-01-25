package com.andyc.walkaround.presentation.venue_discovery

import com.andyc.walkaround.presentation.venue_discovery.model.VenueUi

sealed interface VenueDiscoveryAction {
    data class OnFavoriteToggle(val venueUi: VenueUi): VenueDiscoveryAction
}