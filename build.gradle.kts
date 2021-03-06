buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(ClassPath.kotlinPlugin)
        classpath(ClassPath.buildTool)
        classpath(ClassPath.sqlDelight)
        classpath(ClassPath.googleServices)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
