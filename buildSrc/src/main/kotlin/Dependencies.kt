object Versions {
    const val dateTime = "0.2.1"
    const val kotlin = "1.5.21"
    const val gradle = "7.1.0-alpha03"
    const val kotlinCoroutines = "1.4.3-native-mt"
    const val ktor = "1.5.3"
    const val kotlinxSerialization = "1.1.0"
    const val koin = "3.0.2"
    const val sqlDelight = "1.5.0"
    const val kermit = "0.1.9"

    const val slf4j = "1.7.30"
    const val compose = "1.0.1"
    const val nav_compose = "1.0.0-alpha10"
    const val constraint_compose = "1.0.0-beta02"
    const val activity_compose = "1.3.1"

    const val junit = "4.13"
    const val testRunner = "1.3.0"
    const val material = "1.3.0"
    const val appCompact = "1.3.0"
    const val coil = "0.8.1"
    const val accompanist_coil = "0.15.0"
    const val leakCanaryVersion = "2.7"
}

object Application {
    const val versionName = "1.0"
    const val applicationId = "com.sifat.slushflicks"
    const val versionCode = 1
}

object AndroidSdk {
    const val min = 21
    const val compile = 30
    const val target = compile
}

object ClassPath {
    const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
    const val buildTool = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
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
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint_compose}"
    const val activity = "androidx.activity:activity-compose:${Versions.activity_compose}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val test = "io.insert-koin:koin-test:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
    const val compose = "io.insert-koin:koin-androidx-compose:3.0.1"
}

object Ktor {
    const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

    const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
}

object Coil {
    const val coilAccompanist =
        "com.google.accompanist:accompanist-coil:${Versions.accompanist_coil}"
}

object Serialization {
    const val core =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
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
