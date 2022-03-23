plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = (ConfigData.compileSdkVersion)

    defaultConfig {
        applicationId = "com.mehmetalioyur.recipeapp"
        minSdk = (ConfigData.minSdkVersion)
        targetSdk = (ConfigData.targetSdkVersion)
        versionCode = (ConfigData.versionCode)
        versionName = (ConfigData.versionName)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isDebuggable = true
            isJniDebuggable = true
            isRenderscriptDebuggable = true

            isMinifyEnabled = false
        }
        release {
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        flavorDimensions.add("api")

        productFlavors {

            create("dev") {
                this.dimension = "api"
                buildConfigField("String", "BASE_URL", "\"https://www.themealdb.com/\"")
            }
            create("pilot") {
                this.dimension = "api"
                buildConfigField("String", "BASE_URL", "\"https://www.themealdb.com/\"")
            }
            create("prod") {
                this.dimension = "api"
                buildConfigField("String", "BASE_URL", "\"https://www.themealdb.com/\"")
            }
        }


    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Dependencies.Deps.core)
    implementation(Dependencies.Deps.appCompat)
    implementation("com.google.android.material:material:1.5.0") //i couldn't find the solution.
    implementation(Dependencies.Deps.constraintLayout)
    implementation(Dependencies.Deps.legacySupport)
    implementation(Dependencies.Deps.workRuntime)
    testImplementation(Dependencies.Deps.jUnit)
    androidTestImplementation(Dependencies.Deps.jUnitTest)
    androidTestImplementation(Dependencies.Deps.espesso)

    // Coroutines
    implementation(Dependencies.Deps.coroutinesCore)
    implementation(Dependencies.Deps.coroutinesAndroid)

    // Coroutine Lifecycle Scopes

    implementation(Dependencies.Deps.lifecycleExtensions)
    implementation(Dependencies.Deps.lifecycleViewModel)
    implementation(Dependencies.Deps.lifecycleLiveData)
    implementation(Dependencies.Deps.lifecycleRuntime)

    //Dagger - Hilt
    implementation(Dependencies.Deps.hiltAndroid)
    kapt(Dependencies.Deps.hiltAndroidCompiler)
    implementation(Dependencies.Deps.hiltLifecycleViewModel)
    kapt(Dependencies.Deps.hiltCompiler)


    // Retrofit
    implementation(Dependencies.Deps.retrofit)
    implementation(Dependencies.Deps.retrofitConverterGson)
    implementation(Dependencies.Deps.okHttp)
    implementation(Dependencies.Deps.okHttpLoggingIntercetor)

    //Navigation
    implementation(Dependencies.Deps.navigationFragment)
    implementation(Dependencies.Deps.navigationUi)

    //room
    implementation(Dependencies.Deps.roomRuntime)
    kapt(Dependencies.Deps.roomCompiler)
    implementation(Dependencies.Deps.roomKtx)

    //Glide
    implementation(Dependencies.Deps.glide)
    kapt(Dependencies.Deps.glideCompiler)

    //Lottie
    implementation(Dependencies.Deps.lottie)


}