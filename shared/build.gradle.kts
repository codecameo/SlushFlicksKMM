import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(Plugin.multiPlatform)
    kotlin(Plugin.nativeCocoapods)
    kotlin(Plugin.serialization) version Versions.kotlin
    id(Plugin.android)
    id(Plugin.sqlDelight)
}

version = Application.versionName

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosSlushFlicks/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                /*implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}") {
                    isForce = true
                }*/

                implementation(Deps.datetime)

                // Ktor
                implementation(Ktor.clientCore)
                implementation(Ktor.clientJson)
                implementation(Ktor.clientLogging)
                implementation(Ktor.clientSerialization)

                // Kotlinx Serialization
                implementation(Serialization.core)

                // SQL Delight
                implementation(SqlDelight.runtime)
                implementation(SqlDelight.coroutineExtensions)

                // Firestore
                // implementation(Firebase.analytics)
                // implementation(Firebase.crashlytics)
                // implementation(Firebase.dynamicLink)

                // koin
                api(Koin.core)
                api(Koin.test)

                // kermit
                api(Deps.kermit)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.clientAndroid)
                implementation(SqlDelight.androidDriver)
                implementation(Firebase.firestore)
                implementation(Grpc.grpc)
                // implementation("com.google.firebase:firebase-bom:28.4.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Ktor.clientIos)
                implementation(SqlDelight.nativeDriver)
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdk = AndroidSdk.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
    }
}

sqldelight {
    database("SlushFlickDb") {
        packageName = "com.sifat.slushflicks.data.cache"
        sourceFolders = listOf("sqldelight")
    }
}
