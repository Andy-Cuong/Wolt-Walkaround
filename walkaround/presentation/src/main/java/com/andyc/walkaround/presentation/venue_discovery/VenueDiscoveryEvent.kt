package com.andyc.walkaround.presentation.venue_discovery

import com.andyc.core.presentation.ui.UiText

sealed interface VenueDiscoveryEvent {
    data class SaveFavoriteVenueSuccess(val venueName: UiText): VenueDiscoveryEvent
    data class RemoveFavoriteVenue(val venueName: UiText): VenueDiscoveryEvent
    data object SaveFavoriteVenueFail: VenueDiscoveryEvent
}
