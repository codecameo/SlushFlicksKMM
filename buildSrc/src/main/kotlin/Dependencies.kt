object Versions {
    const val dateTime = "0.2.1"
    const val kotlin = "1.5.21"
    const val gradle = "7.1.0-alpha03"
    const val kotlinCoroutines = "1.4.3-native-mt"
    const val ktor = "1.5.3"
    const val kotlinxSerialization = "1.1.0"
    const val koin = "3.1.2"
    const val sqlDelight = "1.5.0"
    const val kermit = "0.1.9"

    const val slf4j = "1.7.30"
    const val compose = "1.0.2"
    const val nav_compose = "2.4.0-alpha07"
    const val constraintCompose = "1.0.0-beta02"
    const val activityCompose = "1.3.1"

    const val junit = "4.13"
    const val testRunner = "1.3.0"
    const val material = "1.3.0"
    const val appCompact = "1.3.0"
    const val coil = "0.8.1"
    const val accompanistCoil = "0.15.0"
    const val leakCanaryVersion = "2.7"

    const val analytics = "17.4.1"
    const val crashlytics = "17.0.0"
    const val firestore = "23.0.3"
    const val dynamicLinks = "20.1.1"

    const val googleServices = "4.3.10"

    const val firebaseBom = "28.4.0"
    const val grpc = "1.40.0"

    const val accompanistVersion = "0.17.0"
}

object Application {
    const val versionName = "1.0"
    const val applicationId = "com.sifat.slushflicks"
    const val versionCode = 1
}

object AndroidSdk {
    const val min = 21
    const val compile = 31
    const val target = compile
}

object ClassPath {
    const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
    const val buildTool = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
}

object Plugin {
    const val multiPlatform = "multiplatform"
    const val nativeCocoapods = "native.cocoapods"
    const val android = "com.android.library"
    const val serialization = "plugin.serialization"
    const val sqlDelight = "com.squareup.sqldelight"
    const val kapt = "kapt"
}

object Deps {
    const val kermit = "co.touchlab:kermit:${Versions.kermit}"
    const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTime}"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompact}"
}

object Google {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val swiperefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanistVersion}"
    const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanistVersion}"
    const val systemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanistVersion}"
}

object Accompanist {
    const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanistVersion}"
}

object SquareUp {
    const val leakCanary =
        "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanaryVersion}"
}

object Test {
    const val junit = "junit:junit:${Versions.junit}"
}

object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.nav_compose}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val compiler = "androidx.compose.compiler:compiler:${Versions.compose}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintCompose}"
    const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val test = "io.insert-koin:koin-test:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
    const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object Ktor {
    const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

    const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
}

object Coil {
    const val coilAccompanist =
        "com.google.accompanist:accompanist-coil:${Versions.accompanistCoil}"
}

object Grpc {
    const val grpc = "io.grpc:grpc-okhttp:${Versions.grpc}"
}

object Serialization {
    const val core =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
}

object Firebase {
    const val analytics = "com.google.firebase:firebase-analytics:${Versions.analytics}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.crashlytics}"
    const val firestore = "com.google.firebase:firebase-firestore-ktx:${Versions.firestore}"
    const val dynamicLink = "com.google.firebase:firebase-dynamic-links-ktx:${Versions.dynamicLinks}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
}

object SqlDelight {
    const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    const val coroutineExtensions =
        "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
    const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"

    const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    const val nativeDriverMacos =
        "com.squareup.sqldelight:native-driver-macosx64:${Versions.sqlDelight}"
    const val sqlliteDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"
}

object Coroutine {
    const val coroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
}
