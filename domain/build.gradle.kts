plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.common.android.plugin)
    alias(libs.plugins.common.hilt.plugin)
}


android {
    namespace = "com.rezaie.domain"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
}