object AndroidConfig {
    const val compileSdkVersion = 33
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 24
    const val targetSdkVersion = 33
    const val version = "1.0"
}

object Versions {
    const val espresso = "3.5.1"
    const val kotlin = "1.8.10"
    const val gradle = "7.2.2"
    const val junit = "4.13.2"
    const val core_ktx = "1.10.0"
    const val androidx_appcompat = "1.6.1"
    const val androidx_constraintlayout = "2.1.4"
    const val routing_navigator = "1.0.0"
    const val coroutines = "1.6.4"
    const val retrofit = "2.9.0"
    const val retrofit_moshi = "2.6.2"
    const val logging_interceptor = "4.8.0"
    const val hilt = "2.44"
    const val groupie = "2.9.0"
    const val room = "2.2.5"
    const val android_lifecycle = "2.5.1"
    const val timber = "1.5.1"
    const val android_navigation = "2.5.3"
    const val detekt = "1.15.0"
    const val mockk = "1.10.5"
    const val compose = "1.4.0"
    const val multidex = "1.0.3"
    const val activity_compose = "1.7.0"
    const val viewmodel_compose = "2.6.1"
    const val navigation_compose = "2.5.3"
    const val accompanist = "0.30.1"
    const val lottie = "5.2.0"
}

object AndroidLib {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val androidx_core = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"
    const val routing_navigator = "com.github.florent37:navigator:${Versions.routing_navigator}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val retrofit_android = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_moshi}"
    const val gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp_logging =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_processor_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val groupie = "com.xwray:groupie:${Versions.groupie}"
    const val groupie_viewbinding = "com.xwray:groupie-viewbinding:${Versions.groupie}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_coroutine = "androidx.room:room-ktx:${Versions.room}"
    const val viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.android_lifecycle}"
    const val viewmodel_runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.android_lifecycle}"
    const val viewmodel_extension = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    const val viewmodel_compiler =
        "androidx.lifecycle:lifecycle-compiler:${Versions.android_lifecycle}"
    const val timber = "com.github.ajalt:timberkt:${Versions.timber}"
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val material_compose = "androidx.compose.material:material:${Versions.compose}"
    const val preview_compose = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val viewmodel_compose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewmodel_compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation_compose}"
    const val hilt_navigation_compose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val accompanist = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"
    const val lottie_compose = "com.airbnb.android:lottie-compose:${Versions.lottie}"
}

object AndroidTestLib {
    const val junit_lib = "junit:junit:${Versions.junit}"
    const val android_test_junit = "androidx.test.ext:junit:1.1.5"
    const val android_test_espresso_core =
        "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val junit_compose = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val compose_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val compose_test = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val test_core = "androidx.test:core:1.5.0"
    const val mockito = "io.mockk:mockk:${Versions.mockk}"
    const val core_testing = "androidx.arch.core:core-testing:2.2.0"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}