package com.andyc.walkaround.presentation.venue_discovery.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.andyc.core.domain.location.Location
import com.andyc.core.presentation.designsystem.FavoriteIcon
import com.andyc.core.presentation.designsystem.NoImageIcon
import com.andyc.core.presentation.designsystem.NonFavoriteIcon
import com.andyc.core.presentation.designsystem.WoltWalkaroundTheme
import com.andyc.walkaround.presentation.R
import com.andyc.walkaround.presentation.venue_discovery.model.VenueUi

@Composable
fun VenueListItem(
    venue: VenueUi,
    distanceMeters: String,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp)),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = venue.name,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            modifier = Modifier
                .padding(8.dp)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(15.dp)
                ),
            overlineContent = {
                val overlineText = stringResource(
                    R.string.distance_from_you,
                    venue.address,
                    distanceMeters
                )
                Text(
                    text = overlineText,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            supportingContent = {
                Text(
                    text = venue.shortDescription,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingContent = {
                VenueImage(
                    imageUrl = venue.imageUrl,
                    contentDescription = "${venue.name}: ${venue.shortDescription}"
                )
            },
            trailingContent = {
                FavoriteSection(
                    isFavorite = isFavorite,
                    onFavoriteToggle = onFavoriteToggle,
                    score = venue.score
                )
            }
        )
    }
}

@Composable
fun VenueImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(72.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp)),
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    strokeWidth = 1.dp
                )
            },
            error = {
                Icon(
                    imageVector = NoImageIcon,
                    contentDescription = stringResource(R.string.error_failed_to_load_image),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                )
            },
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun FavoriteSection(
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    score: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onFavoriteToggle,
        ) {
            Icon(
                imageVector = if (isFavorite) FavoriteIcon else NonFavoriteIcon,
                contentDescription = if (isFavorite) stringResource(R.string.your_favorite)
                    else stringResource(R.string.not_favorite),
                tint = if (isFavorite) Color.Red.copy(alpha = 0.6f)
                    else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.score),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = score,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun VenueListItemPrev() {
    WoltWalkaroundTheme {
        VenueListItem(
            venue = VenueUi(
                id = "298dsdfl",
                name = "Jungle Juice Bar Kamppi Bussiterminaali",
                address = "Savilahden katu",
                shortDescription = "Maximized taste. We are simply the best choice around",
                imageUrl = "",
                location = Location(60.1456872, 24.2132348),
                score = "8.9/10"
            ),
            distanceMeters = "312m",
            isFavorite = true,
            onFavoriteToggle = { }
        )
    }
}