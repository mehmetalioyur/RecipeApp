plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.mehmetalioyur.recipeapp"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.mehmetalioyur.recipeapp"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }
        release {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "api"

    productFlavors {
        create("dev") {
            dimension = "api"
            buildConfigField("String", "BASE_URL", "\"https://www.themealdb.com/\"")
        }
        create("pilot") {
            dimension = "api"
            buildConfigField("String", "BASE_URL", "\"https://www.themealdb.com/\"")
        }
        create("prod") {
            dimension = "api"
            buildConfigField("String", "BASE_URL", "\"https://www.themealdb.com/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    // AndroidX Core
    implementation(Dependencies.Deps.core)
    implementation(Dependencies.Deps.appCompat)
    implementation(Dependencies.Deps.material)
    implementation(Dependencies.Deps.constraintLayout)
    implementation(Dependencies.Deps.legacySupport)
    implementation(Dependencies.Deps.workRuntime)

    // Test
    testImplementation(Dependencies.Deps.jUnit)
    androidTestImplementation(Dependencies.Deps.jUnitTest)
    androidTestImplementation(Dependencies.Deps.espresso)

    // Coroutines
    implementation(Dependencies.Deps.coroutinesCore)
    implementation(Dependencies.Deps.coroutinesAndroid)

    // Lifecycle
    implementation(Dependencies.Deps.lifecycleExtensions) // deprecated
    implementation(Dependencies.Deps.lifecycleViewModel)
    implementation(Dependencies.Deps.lifecycleLiveData)
    implementation(Dependencies.Deps.lifecycleRuntime)

    // Hilt
    implementation(Dependencies.Deps.hiltAndroid)
    kapt(Dependencies.Deps.hiltAndroidCompiler)
    implementation(Dependencies.Deps.hiltNavigationFragment)
    kapt(Dependencies.Deps.hiltCompiler)

    // Retrofit + OkHttp
    implementation(Dependencies.Deps.retrofit)
    implementation(Dependencies.Deps.retrofitConverterGson)
    implementation(Dependencies.Deps.okHttp)
    implementation(Dependencies.Deps.okHttpLoggingInterceptor)

    // Navigation
    implementation(Dependencies.Deps.navigationFragment)
    implementation(Dependencies.Deps.navigationUi)

    // Room
    implementation(Dependencies.Deps.roomRuntime)
    kapt(Dependencies.Deps.roomCompiler)
    implementation(Dependencies.Deps.roomKtx)

    // Glide
    implementation(Dependencies.Deps.glide)
    kapt(Dependencies.Deps.glideCompiler)

    // Lottie
    implementation(Dependencies.Deps.lottie)
}
