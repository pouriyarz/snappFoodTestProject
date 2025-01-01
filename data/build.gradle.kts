plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.common.android.plugin)
    alias(libs.plugins.common.hilt.plugin)
    alias(libs.plugins.common.serialization.plugin)
}

android {
    namespace = "com.rezaie.data"
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://swapi.py4e.com/api/\"")
        buildConfigField("String", "DATABASE_NAME", "\"characters\"")
    }
    android.buildFeatures.buildConfig = true
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(projects.domain.domainCharacteres)
    implementation(projects.core)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.okHttp.logger)

    //paging
    implementation(libs.paging)

    //room
    implementation(libs.room.common)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
}