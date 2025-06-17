import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

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
                implementation(projects.core.testing)

                implementation(libs.glide.compose)
            }
        }

        androidTarget {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            instrumentedTestVariant {
                sourceSetTree.set(KotlinSourceSetTree.test)

                dependencies {
                    implementation(libs.compose.ui.test.junit4)
                    implementation(libs.compose.ui.test.manifest)
                    implementation(libs.androidx.junit)
                    implementation(libs.espresso.core)
                }
            }
        }

        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.bundles.koin)
                implementation(libs.bundles.ktor)
                implementation(libs.paging.common)
                implementation(libs.paging.compose.common)

                implementation(projects.core.common)
                implementation(projects.core.domain)
                implementation(projects.core.model)
            }
        }
    }
}
