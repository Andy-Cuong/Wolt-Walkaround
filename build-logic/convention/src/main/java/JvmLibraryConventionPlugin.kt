import com.andyc.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Custom Gradle plugin to configure JVM library module
 */
class JvmLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("org.jetbrains.kotlin.jvm")
            }

            configureKotlinJvm()
        }
    }
}