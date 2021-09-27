plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.targetSdk

    defaultConfig {
        applicationId = AppConfig.applicationIdConsumer
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release"){
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
    implementation(Dependencies.circle_image_view)

    implementation(Dependencies.glide)
    kapt(Dependencies.glide_compiler)
    implementation(Dependencies.glide_integration){
        exclude(group, "glide-parent")
    }

    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.junit_ext)
    androidTestImplementation(TestDependencies.espresso)

    //koin dependency injection
    implementation(Dependencies.koin)

    //ViewModel and livedata
    implementation(Dependencies.lifecycle_ext)
    implementation(Dependencies.live_data)
    implementation(Dependencies.view_model)
}