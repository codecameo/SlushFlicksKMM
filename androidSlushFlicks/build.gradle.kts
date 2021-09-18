plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))

    implementation(AndroidX.appCompat)

    // Kotlin 1.5.10 is not compatible with compose version
    implementation(Compose.runtime)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiTooling)
    implementation(Compose.foundationLayout)
    implementation(Compose.compiler)
    implementation(Compose.constraintLayout)
    implementation(Compose.activity)
    implementation(Compose.navigation)
    implementation(Compose.uiGraphics)

    implementation(Google.material)
    implementation(Google.swiperefresh)
    implementation(Google.insets)
    implementation(Google.systemUiController)
    implementation(Deps.datetime)

    implementation(Coil.coilAccompanist)
    implementation(Accompanist.navigationAnimation)

    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.compose)
    // implementation(Firebase.dynamicLink)
    // implementation("com.google.firebase:firebase-common-ktx:19.5.0")

    debugImplementation(SquareUp.leakCanary)
}

android {
    compileSdk = AndroidSdk.compile
    defaultConfig {
        applicationId = Application.applicationId
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = Application.versionCode
        versionName = Application.versionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}
