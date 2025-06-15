package com.vanluong.convention.extensions

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Created by van.luong
 * on 15,June,2025
 */
internal fun KotlinMultiplatformExtension.configurePlatformTargets(
    isIosEnabled: Boolean,
) {
    if (isIosEnabled) {
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "TMDB"
                isStatic = true
            }
        }
    }
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}