package com.andyc.walkaround.domain

import com.andyc.core.domain.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationObserver {
    fun observeLocation(intervalMillis: Long): Flow<Location>
}