@file:OptIn(ExperimentalMaterial3Api::class)

package com.andyc.walkaround.presentation.venue_discovery

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andyc.core.domain.location.Location
import com.andyc.core.domain.venue.Venue
import com.andyc.core.presentation.designsystem.WoltWalkaroundTheme
import com.andyc.core.presentation.designsystem.components.WalkaroundToolbar
import com.andyc.core.presentation.ui.ObserveAsEvents
import com.andyc.core.presentation.ui.toFormattedMeters
import com.andyc.walkaround.presentation.R
import com.andyc.walkaround.presentation.venue_discovery.components.VenueListItem
import com.andyc.walkaround.presentation.venue_discovery.mapper.toVenueUi
import org.koin.androidx.compose.koinViewModel

@Composable
fun VenueDiscoveryScreenRoot(
    viewModel: VenueDiscoveryViewModel = koinViewModel()
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is VenueDiscoveryEvent.SaveFavoriteVenueSuccess -> {
                Toast.makeText(
                    context,
                    event.venueName.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
            VenueDiscoveryEvent.SaveFavoriteVenueFail -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.error_failed_to_add_to_favorite),
                    Toast.LENGTH_LONG
                ).show()
            }
            is VenueDiscoveryEvent.RemoveFavoriteVenue -> {
                Toast.makeText(
                    context,
                    event.venueName.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    VenueDiscoveryScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
fun VenueDiscoveryScreen(
    state: VenueDiscoveryState,
    onAction: (VenueDiscoveryAction) -> Unit,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WalkaroundToolbar(
                title = stringResource(R.string.wolt_walkaround),
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        if (state.nearbyVenues.isEmpty()) {
            EmptyVenueDiscoveryScreen(modifier = Modifier.padding(padding))
        } else {
            val lazyColumnState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background),
                state = lazyColumnState,
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(items = state.nearbyVenues, key = { venue -> venue.id }) {
                    val distanceMeters by animateFloatAsState(
                        targetValue = it.location.distanceTo(state.currentLocation)
                    )

                    VenueListItem(
                        venue = it,
                        distanceMeters = distanceMeters.toFormattedMeters(),
                        onFavoriteToggle = { onAction(VenueDiscoveryAction.OnFavoriteToggle(it)) },
                        isFavorite = state.favoriteVenues.any { venue -> venue.id == it.id },
                        modifier = Modifier
                            .animateItem()
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyVenueDiscoveryScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No nearby venues. Try walking around?",
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
private fun VenueDiscoveryScreenPrev() {
    WoltWalkaroundTheme {
        VenueDiscoveryScreen(
            state = VenueDiscoveryState(
                currentLocation = Location(60.169108, 24.936210),
                nearbyVenues = listOf(
                    Venue(
                        id = "7986f87sd9f",
                        name = "Pizza Max",
                        address = "Raviradantie",
                        shortDescription = "Simply the best",
                        imageUrl = "",
                        location = Location(60.16896328076366, 24.930145740509033),
                        score = 8.5
                    ).toVenueUi(),
                    Venue(
                        id = "798687393d9f",
                        name = "Jungle Juice Bar Kamppi Bussiterminaali",
                        address = "Savilahde",
                        shortDescription = "Definitely not the worst",
                        imageUrl = "",
                        location = Location(60.16915669999999, 24.9332274),
                        score = 8.9
                    ).toVenueUi(),
                ),
                favoriteVenues = listOf(
                    Venue(
                        id = "7986f87sd9f",
                        name = "Pizza Max",
                        address = "Raviradantie",
                        shortDescription = "Simply the best",
                        imageUrl = "",
                        location = Location(60.16896328076366, 24.930145740509033),
                        score = 8.5
                    )
                )
            ),
            onAction = {}
        )
    }
}