import com.andyc.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Custom Gradle plugin for Android test source set
 */
class AndroidJUnit5ConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("woltwalkaround.jvm.junit5")
                apply("de.mannodermaus.android-junit5")
            }

            dependencies {
                "androidTestImplementation"(libs.findLibrary("junit5.api").get())
                "androidTestImplementation"(libs.findLibrary("junit5.params").get())
                "androidTestImplementation"(libs.findLibrary("junit5.android.test.compose").get())
                "debugImplementation"(libs.findLibrary("androidx.ui.test.manifest").get())
                "androidTestRuntimeOnly"(libs.findLibrary("junit5.engine").get())

                "androidTestImplementation"(libs.findLibrary("assertk").get())
                "androidTestImplementation"(libs.findLibrary("coroutines.test").get())
                "androidTestImplementation"(libs.findLibrary("turbine").get())
                "androidTestImplementation"(libs.findBundle("ktor").get())
                "androidTestImplementation"(libs.findLibrary("ktor.client.mock").get())

//                "androidTestImplementation"(project(":core:android-test-util"))
            }
        }
    }
}