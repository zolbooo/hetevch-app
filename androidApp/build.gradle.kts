plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "xyz.zolbooo.hetevch.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "xyz.zolbooo.hetevch.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            // TODO: remove when https://github.com/Kotlin/kotlinx.coroutines/issues/3668 is closed
            excludes += "META-INF/versions/9/previous-compilation-data.bin"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material")
    implementation("androidx.activity:activity-compose:1.7.0")

    implementation("io.insert-koin:koin-android:3.4.0")
}