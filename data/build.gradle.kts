plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.apollo.graphql)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

apollo {
    service("service") {
        packageName.set("com.example.radiofrancetest.android.graphql")
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.apollo.graphql)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.koin.core)
}