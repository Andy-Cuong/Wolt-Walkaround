plugins {
    alias(libs.plugins.woltwalkaround.android.library)
}

android {
    namespace = "com.andyc.walkaround.data"
}

dependencies {

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.walkaround.domain)
    implementation(projects.core.domain)
    implementation(projects.core.database)
}