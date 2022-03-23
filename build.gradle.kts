// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.BuildPlugins.kotlin)
        classpath(Dependencies.BuildPlugins.android)
        classpath(Dependencies.BuildPlugins.hilt)
        classpath(Dependencies.BuildPlugins.navigation)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}


