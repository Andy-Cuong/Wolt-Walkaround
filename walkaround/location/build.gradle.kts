plugins {
    alias(libs.plugins.woltwalkaround.android.library)
}

android {
    namespace = "com.andyc.walkaround.location"
}

dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.walkaround.domain)
}