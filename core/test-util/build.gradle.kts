plugins {
    alias(libs.plugins.woltwalkaround.jvm.library)
    alias(libs.plugins.woltwalkaround.jvm.junit5)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ktor.client.mock)
    implementation(libs.bundles.ktor)
    implementation(libs.coroutines.test)

    implementation(libs.junit5.api)
    implementation(libs.coroutines.test)

    implementation(projects.core.domain)
}