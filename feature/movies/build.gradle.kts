plugins {
    alias(libs.plugins.tmdb.kmp.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        androidMain {
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

                implementation(projects.core.common)
                implementation(projects.core.domain)
                implementation(projects.core.model)
                implementation(projects.core.ui)

                implementation(libs.glide.compose)
            }
        }

        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.bundles.koin)
                implementation(libs.bundles.ktor)

                implementation(projects.core.common)
                implementation(projects.core.domain)
                implementation(projects.core.model)
            }
        }
    }
}
