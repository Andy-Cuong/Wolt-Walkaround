import com.android.build.api.dsl.ApplicationExtension
import com.andyc.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * Custom Gradle plugin that applies the [AndroidApplicationConventionPlugin] and additional
 * configuration for Compose application
 */
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("woltwalkaround.android.application")
                // Use Version Catalog
//                apply(libs.findPlugin("compose.compiler").get().get().pluginId)
//                apply(libs.findPlugin("woltwalkaround.android.application").get().get().pluginId)
            }

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}