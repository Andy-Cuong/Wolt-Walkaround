plugins {
    alias(libs.plugins.woltwalkaround.android.library.compose)
}

android {
    namespace = "com.andyc.core.presentation.ui"
}

dependencies {

    implementation(libs.bundles.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
}