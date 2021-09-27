plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.targetSdk
    buildToolsVersion = AppConfig.buildToolVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug"){
            buildConfigField("String", "BASE_URL", AppConfig.base_url)
            buildConfigField("String", "TOKEN", AppConfig.token)
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("release"){
            buildConfigField("String", "BASE_URL", AppConfig.base_url)
            buildConfigField("String", "TOKEN", AppConfig.token)
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(Dependencies.androidX_core_ktx)
    implementation(Dependencies.androidX_app_compact)
    implementation(Dependencies.material_design)
    implementation(Dependencies.constrain_layout)
    implementation(Dependencies.lottie)
    implementation(Dependencies.circle_image_view)

    implementation(Dependencies.glide)
    kapt(Dependencies.glide_compiler)
    implementation(Dependencies.glide_integration){
        exclude(group,"glide-parent")
    }

    //testing
    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.junit_ext)
    androidTestImplementation(TestDependencies.espresso)

    //ViewModel and livedata
    implementation(Dependencies.lifecycle_ext)
    implementation(Dependencies.live_data)
    implementation(Dependencies.view_model)

    //koin dependency injection
    implementation(Dependencies.koin)

    //navigation component
    implementation(Dependencies.nav_fragment)
    implementation(Dependencies.nav_ui)

    //networking
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshi_converter)
    implementation(Dependencies.interceptor)

    //pagination
    implementation(Dependencies.pagination)

    implementation(Dependencies.room)
    implementation(Dependencies.room_runtime)
    kapt(Dependencies.room_compiler)

    implementation(Dependencies.preference_ktx)
}