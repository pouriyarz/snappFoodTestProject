plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.common.android.plugin)
    alias(libs.plugins.common.hilt.plugin)
    alias(libs.plugins.common.serialization.plugin)
}

android {
    namespace = "com.rezaie.data"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
}