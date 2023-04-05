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
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
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
            isMinifyEnabled = true
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(platform(Libraries.Compose.bom))
    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.uiTooling)
    implementation(Libraries.Compose.uiToolingPreview)
    implementation(Libraries.Compose.foundation)
    implementation(Libraries.Compose.material)

    implementation(Libraries.activityCompose)
    implementation(Libraries.koinAndroid)
}