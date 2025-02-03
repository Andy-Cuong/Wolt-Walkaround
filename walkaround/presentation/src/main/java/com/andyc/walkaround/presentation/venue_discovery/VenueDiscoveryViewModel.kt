package com.andyc.walkaround.presentation.venue_discovery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andyc.core.domain.location.Location
import com.andyc.core.domain.util.Result
import com.andyc.core.domain.venue.Venue
import com.andyc.core.domain.venue.VenueRepository
import com.andyc.core.presentation.ui.UiText
import com.andyc.walkaround.presentation.R
import com.andyc.walkaround.presentation.venue_discovery.mapper.toVenue
import com.andyc.walkaround.presentation.venue_discovery.mapper.toVenueUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VenueDiscoveryViewModel(
    private val venueRepository: VenueRepository
): ViewModel() {
    var state by mutableStateOf(VenueDiscoveryState())
        private set

    private val _eventChannel = Channel<VenueDiscoveryEvent>()
    val events = _eventChannel.receiveAsFlow()

    init {
        venueRepository.observeCurrentLocation()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = Location(0.0, 0.0)
            )
            .onEach { location ->
                val venues = when (val result = venueRepository.getNearbyVenues()) {
                    is Result.Error -> state.nearbyVenues // Keep the previous list
                    is Result.Success -> result.data.map { it.toVenueUi() }
                }
                state = state.copy(
                    currentLocation = location,
                    nearbyVenues = venues
                )
            }
            .launchIn(viewModelScope)

        venueRepository.getFavoriteVenues()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = emptyList()
            )
            .onEach { favoriteVenues ->
                state = state.copy(favoriteVenues = favoriteVenues)
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: VenueDiscoveryAction) {
        when (action) {
            is VenueDiscoveryAction.OnFavoriteToggle -> {
                viewModelScope.launch(Dispatchers.IO) {

                    val venue = action.venueUi.toVenue()
                    toggleFavoriteVenue(venue)
                }
            }
        }
    }

    private suspend fun toggleFavoriteVenue(venue: Venue) {
        if (state.favoriteVenues.contains(venue)) {
            venueRepository.deleteVenueFromFavorite(venue)
            _eventChannel.send(VenueDiscoveryEvent.RemoveFavoriteVenue(
                venueName = UiText.StringResource(
                    R.string.removed_from_favorite,
                    arrayOf(venue.name)
                )
            ))
        } else {
            when (venueRepository.addVenueAsFavorite(venue)) {
                is Result.Error -> {
                    _eventChannel.send(VenueDiscoveryEvent.SaveFavoriteVenueFail)
                }
                is Result.Success -> {
                    _eventChannel.send(VenueDiscoveryEvent.SaveFavoriteVenueSuccess(
                        venueName = UiText.StringResource(
                            R.string.added_to_favorites,
                            arrayOf(venue.name)
                        )
                    ))
                }
            }
        }
    }
}