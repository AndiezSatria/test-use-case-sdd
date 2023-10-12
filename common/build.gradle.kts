plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "org.andiez.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["enableCrashReporting"] = "true"
        }

        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["enableCrashReporting"] = "true"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core")))

    // ViewModel
    implementation(AndroidLib.viewmodel_ktx)
    implementation(AndroidLib.viewmodel_runtime)
    implementation(AndroidLib.viewmodel_extension)
    implementation(AndroidLib.viewmodel_compose)
    kapt(AndroidLib.viewmodel_compiler)

    // Hilt
    implementation(AndroidLib.hilt)
    kapt(AndroidLib.hilt_processor_compiler)

    // Retrofit
    implementation(AndroidLib.retrofit_android)
    implementation(AndroidLib.gson_converter)
    implementation(AndroidLib.okhttp_logging)

    // Room
    implementation(AndroidLib.room)
    implementation(AndroidLib.room_coroutine)
    kapt(AndroidLib.room_compiler)

    // Timber
    implementation(AndroidLib.timber)

    // Camera
    implementation (AndroidLib.camera2)
    implementation(AndroidLib . camera_lifecycle)
    implementation(AndroidLib . camera_view)

    // ZXing
    implementation(AndroidLib.zxing)
}