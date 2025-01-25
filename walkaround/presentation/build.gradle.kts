plugins {
    alias(libs.plugins.woltwalkaround.android.feature.ui)
}

android {
    namespace = "com.andyc.walkaround.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.androidx.activity.compose)

    implementation(libs.bundles.koin)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.core.domain)
    implementation(projects.walkaround.domain)
}