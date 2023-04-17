plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("app.cash.sqldelight") version Versions.sqlDelight
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Hetevch app shared logic: computations and persistence"
        homepage = "https://github.com/zolbooo/hetevch-app/tree/main/shared"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libraries.Common.koinCore)
                implementation(Libraries.Common.kotlinxDatetime)
                implementation(Libraries.Common.kotlinxCoroutinesCore)
                implementation(Libraries.Common.sqlDelightCoroutinesExtension)
                implementation(Libraries.Common.settings)
                implementation(Libraries.Common.settingsCoroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Libraries.UnitTests.Common.coroutinesTest)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Libraries.Android.sqlDelight)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(Libraries.UnitTests.Android.testKtx)
                implementation(Libraries.UnitTests.Android.robolectric)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Libraries.iOS.sqlDelight)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("xyz.zolbooo.hetevch.repository")
        }
    }
}

android {
    namespace = "xyz.zolbooo.hetevch"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}
