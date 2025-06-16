import com.android.build.gradle.LibraryExtension
import com.vanluong.convention.extensions.configureCompileOptions
import com.vanluong.convention.extensions.configureDefaultConfig
import com.vanluong.convention.extensions.configurePlatformTargets
import com.vanluong.convention.extensions.configureTestOptions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Created by van.luong
 * on 15,June,2025
 *
 * This plugin is used to configure Android Kotlin Multi-Platform Library projects.
 */
class AndroidKotlinMultiPlatformLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")

            extensions.configure<LibraryExtension> {
                compileSdk = 35

                configureDefaultConfig()
                configureTestOptions()
                configureCompileOptions()
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configurePlatformTargets(
                    isIosEnabled = false,
                )
            }
        }
    }
}