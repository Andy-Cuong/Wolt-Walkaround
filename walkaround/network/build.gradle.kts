plugins {
    alias(libs.plugins.woltwalkaround.android.library)
    alias(libs.plugins.woltwalkaround.jvm.ktor)
    alias(libs.plugins.woltwalkaround.android.junit5)
}

android {
    namespace = "com.andyc.walkaround.network"
}

dependencies {
    implementation(libs.bundles.koin)

    testImplementation(libs.ktor.client.mock)

    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.walkaround.domain)

    testImplementation(projects.core.testUtil)
    testImplementation(projects.walkaround.location)
}