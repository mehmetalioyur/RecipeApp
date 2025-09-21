object Versions {
    // Build & Tooling
    const val gradleVersion = "8.4.1"                // AGP'nin en son stabil sürümü
    const val kotlinVersion = "1.9.24"               // Kotlin'in en son stabil sürümü

    // AndroidX Core & UI
    const val coreVersion = "1.13.1"                 // androidx.core:core-ktx
    const val appCompatVersion = "1.7.0"             // androidx.appcompat:appcompat
    const val materialVersion = "1.12.0"             // com.google.android.material:material
    const val constraintLayoutVersion = "2.1.4"      // androidx.constraintlayout:constraintlayout
    const val legacySupportVersion = "1.0.0"         // Genellikle güncellenmez
    const val workRuntimeVersion = "2.9.0"           // androidx.work:work-runtime-ktx

    // Test
    const val jUnitVersion = "4.13.2"                // Standart JUnit4
    const val jUnitTestVersion = "1.2.1"             // androidx.test.ext:junit
    const val espressoVersion = "3.6.1"              // androidx.test.espresso:espresso-core

    // Coroutines
    const val coroutinesVersion = "1.8.1"            // kotlinx-coroutines son stabil sürümü

    // Lifecycle
    // Not: lifecycle-extensions kullanımdan kaldırıldı (deprecated).
    // Mümkünse ilgili ViewModel ve LiveData bağımlılıklarını doğrudan kullanın.
    const val lifecycleExtensionsVersion = "2.2.0"
    const val lifecycleVersion = "2.8.1"             // Lifecycle kütüphanelerinin son stabil sürümü

    // Dagger-Hilt
    const val hiltVersion = "2.51.1"                 // Dagger Hilt'in en son stabil sürümü
    // Not: androidx.hilt kütüphaneleri 1.2.0'da stabil oldu ve bazıları yeniden isimlendirildi.
    // hilt-lifecycle-viewmodel yerine hilt-navigation-fragment kullanılması önerilir.
    const val hiltNavigationFragmentVersion = "1.2.0"// androidx.hilt:hilt-navigation-fragment
    const val hiltCompilerVersion = "1.2.0"          // androidx.hilt:hilt-compiler

    // Retrofit & OkHttp
    // Not: Retrofit 3 henüz beta aşamasında. Stabilite için 2.x sürümü önerilir.
    const val retrofitVersion = "2.11.0"             // Retrofit'in son stabil sürümü
    const val okHttpVersion = "4.12.0"               // OkHttp'nin son stabil sürümü (5.x alpha'da)

    // Navigation
    const val navigationVersion = "2.7.7"            // AndroidX Navigation'ın son stabil sürümü

    // Room
    const val roomVersion = "2.6.1"                  // Room'un en son stabil sürümü


    // Glide
    const val glideVersion = "4.16.0"                // Glide'ın en son stabil sürümü

    // Lottie
    const val lottieVersion = "6.4.0"                // Lottie'nin en son stabil sürümü
}