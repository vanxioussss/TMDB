package com.vanluong.convention.extensions

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion

/**
 * Created by van.luong
 * on 15,June,2025
 */

internal fun BaseExtension.configureCompileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

internal fun BaseExtension.configureTestOptions() {
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

internal fun BaseExtension.configureDefaultConfig() {
    defaultConfig {
        namespace = "com.tmdb"
        minSdk = 28
        targetSdk = 35
        versionCode = 1 // This should be replaced with a dynamic versioning system
        versionName = "1.0.0" // This should be replaced with a dynamic versioning system
    }
}