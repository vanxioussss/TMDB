package com.tmdb.database.providers

import androidx.room.Room
import org.koin.mp.KoinPlatform

/**
 * Created by van.luong
 * on 16,June,2025
 */
actual fun databaseInstance(): TMDBDatabase {
    return Room.databaseBuilder(
        KoinPlatform.getKoin().get(),
        TMDBDatabase::class.java,
        dbFileName,
    ).build()
}