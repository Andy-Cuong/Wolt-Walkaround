import com.andyc.convention.addUiLayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Custom Gradle plugin to configure feature UI (presentation) module
 */
class AndroidFeatureUiConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("woltwalkaround.android.library.compose")
            }

            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }
}