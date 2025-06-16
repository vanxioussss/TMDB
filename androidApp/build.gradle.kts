plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.secrets)
    id("kotlin-parcelize")
}

android {
    namespace = "com.tmdb"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.tmdb"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

secrets {
    propertiesFileName = "secrets.properties"
}

dependencies {
    implementation(compose.ui)
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.animation)
    implementation(compose.uiUtil)
    implementation(compose.components.resources)
    implementation(compose.preview)
    implementation(libs.compose.ui.tooling)
    implementation(libs.navigation.compose)

    implementation(libs.kotlin.stdlib)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.ktor)

    implementation(projects.core.bridge)
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.core.ui)

    implementation(projects.feature.movies)

    implementation(libs.glide.compose)
}