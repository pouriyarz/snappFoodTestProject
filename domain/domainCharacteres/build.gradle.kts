plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.common.android.plugin)
    alias(libs.plugins.common.hilt.plugin)
}

android {
    namespace = "com.rezaie.domain.domainCharacteres"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    //room
    implementation(libs.room.common)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)

    //paging
    implementation(libs.paging)

    implementation(projects.core)
    implementation(projects.components)

    //test
    testImplementation(libs.mockk)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.junit)
}