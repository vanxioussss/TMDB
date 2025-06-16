package com.tmbd.android.app

import android.app.Application
import com.tmbd.android.di.appModules
import com.tmbd.android.di.featuresModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.lazyModules

/**
 * Created by van.luong
 * on 17,June,2025
 */
class TMDBApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Koin
        startKoin {
            androidContext(this@TMDBApp)
            modules(appModules)
            lazyModules(featuresModule)
        }
    }
}