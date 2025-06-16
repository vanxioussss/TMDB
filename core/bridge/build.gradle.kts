plugins {
    alias(libs.plugins.tmdb.kmp.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core.domain)
                api(projects.core.data)

                implementation(libs.kotlin.stdlib)
                implementation(libs.koin.core)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}