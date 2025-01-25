plugins {
    alias(libs.plugins.woltwalkaround.android.library)
    alias(libs.plugins.woltwalkaround.jvm.ktor)
}

android {
    namespace = "com.andyc.core.data"
}

dependencies {
    implementation(libs.bundles.koin)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}