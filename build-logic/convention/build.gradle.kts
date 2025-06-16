import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.tmdb.buildlogic" // Package name for the our plugins

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("kmpLibraryPlugin") {
            id = libs.plugins.tmdb.kmp.library.get().pluginId
            implementationClass = "AndroidKotlinMultiPlatformLibraryPlugin"
        }

        register("kmpAppPlugin") {
            id = libs.plugins.tmdb.kmp.app.get().pluginId
            implementationClass = "AndroidKotlinMultiPlatformAppPlugin"
        }
    }
}