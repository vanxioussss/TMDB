plugins {
    alias(libs.plugins.tmdb.kmp.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core.model)
                implementation(projects.core.domain)
                api(projects.core.network)
                api(projects.core.database)
                implementation(projects.core.common)
                implementation(projects.core.testing)

                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.datetime)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.coroutines.core)

                // Paging
                implementation(libs.paging.common)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.mockk)
                implementation(libs.bundles.test)
                implementation(libs.bundles.test.multiplatform)
                implementation(libs.turbine)
            }
        }
    }

}