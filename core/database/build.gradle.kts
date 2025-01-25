plugins {
    alias(libs.plugins.woltwalkaround.android.library)
    alias(libs.plugins.woltwalkaround.android.room)
}

android {
    namespace = "com.andyc.core.database"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
}