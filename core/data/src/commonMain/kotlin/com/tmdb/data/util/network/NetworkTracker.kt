package com.tmdb.data.util.network

import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 17,June,2025
 *
 * Utility for tracking network connectivity status.
 */
interface NetworkTracker {
    val isOnline: Flow<Boolean>
}