package com.andyc.walkaround.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WoltRestaurantResponse(
    val sections: List<Section>
)

@Serializable
data class Section(
    val items: List<Item> = emptyList()
)

@Serializable
data class Item(
    val image: Image,
    val venue: VenueDto? = null
)

@Serializable
data class Image(
    val url: String
)

@Serializable
data class VenueDto(
    val address: String,
    val id: String,
    val location: List<Double>,
    val name: String,
    val rating: Rating? = null,
    @SerialName("short_description") val shortDescription: String = ""
)

@Serializable
data class Rating(
    val score: Double
)