plugins {
    alias(libs.plugins.woltwalkaround.android.application.compose)
    alias(libs.plugins.woltwalkaround.jvm.ktor)
}

android {
    namespace = "com.andyc.woltwalkaround"
}

dependencies {
    // Coil
    implementation(libs.coil.compose)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Koin
    implementation(libs.bundles.koin)

    // Compose
    implementation(libs.bundles.compose)
    implementation(platform(libs.androidx.compose.bom))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Splash screen
    implementation(libs.androidx.core.splashscreen)

    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)

    implementation(projects.walkaround.data)
    implementation(projects.walkaround.domain)
    implementation(projects.walkaround.location)
    implementation(projects.walkaround.network)
    implementation(projects.walkaround.presentation)
}