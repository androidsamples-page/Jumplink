@file:Suppress("UnstableApiUsage")

plugins {
    id("jumplink.android.library")
}

android {
    namespace = "core.libraries.design"
}

dependencies {

    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.appcompat)
    implementation (libs.google.material)

    // Import the Compose BOM
    implementation (platform(libs.compose.bom))

    // Override Material Design 3 library version with a pre-release version
    implementation (libs.compose.material3)
    implementation (libs.activity.compose)
    implementation (libs.compose.ui)
    implementation (libs.compose.ui.tooling.preview)
    implementation (libs.compose.runtime.livedata)
    implementation (libs.navigation.compose)

}