package com.andyc.core.presentation.ui

import kotlin.math.roundToInt

fun Float.toFormattedMeters(): String {
    return "${this.roundToInt()}m"
}