plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}
buildscript {
    dependencies {
        classpath(libs.classPath.navigation.safeargs)
        classpath(libs.classPath.serializable)
        classpath(libs.gradleAndroid)
    }
}

true