plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.common.android.plugin)
    alias(libs.plugins.common.hilt.plugin)
    id(libs.plugins.navigation.safeArgs.get().pluginId)
    alias(libs.plugins.common.serialization.plugin)
}

android {
    namespace = "com.rezaie.feature"

    buildFeatures {
        compose = true
    }
    android.buildFeatures.buildConfig = true

    defaultConfig {
        buildConfigField("String", "BASE_URL_IMAGE", "\"https://starwars-visualguide.com/assets/img/characters/\"")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.fragment)
    implementation(libs.androidx.ui.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //project layers
    implementation(projects.domain.domainCharacteres)
    implementation(projects.domain.domainCore)
    implementation(projects.core)
    implementation(projects.components)

    implementation(libs.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.testing)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    //paging
    implementation(libs.paging)

    implementation ("androidx.paging:paging-compose:1.0.0-alpha18")
    implementation ("com.google.accompanist:accompanist-placeholder-material:0.36.0")

}