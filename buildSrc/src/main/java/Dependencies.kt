object Dependencies {
    const val androidX_core_ktx = "androidx.core:core-ktx:${Versions.androidX_core_ktx_version}"
    const val androidX_app_compact = "androidx.appcompat:appcompat:${Versions.androidX_app_compact_version}"
    const val material_design = "com.google.android.material:material:${Versions.material_design_version}"
    const val constrain_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_version}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie_version}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
    const val glide_integration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide_version}"
    const val circle_image_view = "de.hdodenhof:circleimageview:${Versions.circle_imageView}"

    //ViewModel and livedata
    const val lifecycle_ext = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"
    const val live_data = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModel_version}"
    const val view_model = "androidx.lifecycle:lifecycle-viewmodel-ktx:$${Versions.viewModel_version}"

    //koin dependency injection
    const val koin = "io.insert-koin:koin-android:${Versions.koin_version}"

    //navigation component
    const val nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    const val nav_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"

    //networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.moshi_version}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_version}"

    //pagination
    const val pagination = "androidx.paging:paging-runtime-ktx:${Versions.pagination_version}"

    const val room = "androidx.room:room-ktx:${Versions.room_version}"
    const val room_runtime = "androidx.room:room-runtime:${Versions.room_version}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"

    const val preference_ktx = "androidx.preference:preference-ktx:${Versions.preference_version}"

    const val gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
}