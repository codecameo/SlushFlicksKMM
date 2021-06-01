plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))

    implementation(AndroidX.appCompat)

    //Kotlin 1.5.10 is not compatible with compose version
    /*implementation(Compose.runtime)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiTooling)
    implementation(Compose.foundationLayout)
    implementation(Compose.accompanist)
    implementation(Compose.compiler)
    implementation(Compose.constraintLayout)
    implementation(Compose.activity)
    implementation(Compose.navigation)
    implementation(Compose.uiGraphics)*/

    implementation(Google.material)
    implementation(Deps.datetime)

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
        //compose = true
    }

    /*composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }*/
}