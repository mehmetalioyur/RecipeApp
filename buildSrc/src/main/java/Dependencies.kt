object Dependencies {


    object BuildPlugins {
        val android by lazy { "com.android.tools.build:gradle:${Versions.gradleVersion}" }
        val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}" }
        val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}" }
        val navigation by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}" }

    }

    object Deps {

        val core by lazy { "androidx.core:core-ktx:${Versions.coreVersion}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompatVersion}" }
        val material by lazy{"com.google.android.material:${Versions.materialVersion}"}
        val constraintLayout by lazy {"androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"}
        val legacySupport by lazy {"androidx.legacy:legacy-support-v4:${Versions.legacySupportVersion}"}
        val workRuntime by lazy {"androidx.work:work-runtime-ktx:${Versions.workRuntimeVersion}"}
        val jUnit by lazy {"junit:junit:${Versions.jUnitVersion}"}
        val jUnitTest by lazy {"androidx.test.ext:junit:${Versions.jUnitTestVersion}"}
        val espesso by lazy {"androidx.test.espresso:espresso-core:${Versions.espressoVersion}"}

        //Coroutines
        val coroutinesCore by lazy {"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"}
        val coroutinesAndroid by lazy {"org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"}

        //Lifecycler
        val lifecycleExtensions by lazy {"androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensionsVersion}"}
        val lifecycleViewModel by lazy {"androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"}
        val lifecycleLiveData by lazy {"androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"}
        val lifecycleRuntime by lazy {"androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"}

        //Dagger-Hilt
        val hiltAndroid by lazy {"com.google.dagger:hilt-android:${Versions.hiltVersion}"}
        val hiltAndroidCompiler by lazy {"com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"}
        val hiltLifecycleViewModel by lazy {"androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycleVersion}"}
        val hiltCompiler by lazy {"androidx.hilt:hilt-compiler:${Versions.hiltCompilerVersion}"}

        //Retrofit
        val retrofit by lazy {"com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"}
        val retrofitConverterGson by lazy {"com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"}

        //OkHttp
        val okHttp by lazy {"com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"}
        val okHttpLoggingIntercetor by lazy {"com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"}

        //Navigation
        val navigationFragment by lazy {"androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"}
        val navigationUi by lazy {"androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"}

        //Room
        val roomRuntime by lazy {"androidx.room:room-runtime:${Versions.roomVersion}"}
        val roomCompiler by lazy {"androidx.room:room-compiler:${Versions.roomVersion}"}
        val roomKtx by lazy {"androidx.room:room-ktx:${Versions.roomVersion}"}

        //Glide
        val glide by lazy {"com.github.bumptech.glide:glide:${Versions.glideVersion}"}
        val glideCompiler by lazy {"com.github.bumptech.glide:compiler:${Versions.glideVersion}"}

        //Lottie
        val lottie by lazy {"com.airbnb.android:lottie:${Versions.lottieVersion}"}

    }
}
