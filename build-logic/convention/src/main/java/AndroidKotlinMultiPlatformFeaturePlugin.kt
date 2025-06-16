/**
 * Created by van.luong
 * on 16,June,2025
 */
//class AndroidKotlinMultiPlatformFeaturePlugin : Plugin<Project> {
//    override fun apply(target: Project) {
//
//        with(target) {
//            pluginManager.apply("tmdb.kmp.library")
//
//            extensions.configure<KotlinMultiplatformExtension> {
//
//                sourceSets.commonMain.dependencies {
//                    implementation(project(":core:model"))
//                    implementation(project(":core:common"))
//                    implementation(project(":core:domain"))
//                }
//
//                sourceSets.androidMain {
//                    dependencies {
//                        implementation(
//                            libs.findLibrary("foundation")
//                                .get()
//                        )
//                        implementation(
//                            libs.findLibrary("androidx.compose.material3:material3").get()
//                        )
//                        implementation(libs.findLibrary("androidx.compose.ui:ui-tooling").get())
//                        implementation(libs.findLibrary("androidx.compose.ui:ui").get())
//                        implementation(
//                            libs.findLibrary("androidx.compose.ui:ui-tooling-preview").get()
//                        )
//                        implementation(
//                            libs.findLibrary("org.jetbrains.androidx.navigation:navigation-compose")
//                                .get()
//                        )
//                    }
//                }
//            }
//        }
//    }
//}