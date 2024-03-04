plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.relay") version "0.3.00"
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.applecare"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.applecare"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        compose = true
        mlModelBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.compose.ui:ui:1.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.2")
    implementation("androidx.compose.runtime:runtime:1.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-alpha02")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")


    implementation("androidx.core:core-ktx:1.9.0")
//    implementation("org.tensorflow:tensorflow-lite-task-vision:0.4.0")
//    implementation("org.tensorflow:tensorflow-lite-gpu-delegate-plugin:0.4.0")
//    implementation("org.tensorflow:tensorflow-lite-gpu:2.9.0")
//    implementation("org.tensorflow:tensorflow-lite-support:2.8.0")
    implementation ("androidx.compose.material:material-icons-extended:1.5.4")
    implementation ("org.tensorflow:tensorflow-lite:2.15.0")

    implementation("androidx.camera:camera-camera2:1.3.1")
    implementation("androidx.camera:camera-core:1.3.1")
    implementation("androidx.camera:camera-lifecycle:1.3.1")
    implementation("androidx.camera:camera-view:1.3.1")
    implementation("androidx.camera:camera-extensions:1.3.1")

    //Lottie
    implementation("com.airbnb.android:lottie-compose:6.0.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("io.insert-koin:koin-core:3.2.0")// or latest version
    implementation ("io.insert-koin:koin-android:3.2.0")


    implementation ("io.insert-koin:koin-androidx-compose:3.4.6")

    ////KOIN KSP////
    implementation ("io.insert-koin:koin-annotations:1.3.1")
    implementation ("io.insert-koin:koin-ksp-compiler:1.3.1")
    implementation("org.tensorflow:tensorflow-lite-metadata:0.4.3")
    implementation("org.tensorflow:tensorflow-lite-support:0.4.3")
    //ksp ("io.insert-koin:koin-ksp-compiler:1.3.1")
    ksp("androidx.room:room-compiler:2.5.2")
    implementation("io.github.microutils:kotlin-logging:2.0.11")

    implementation ("androidx.compose.material:material:1.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation ("com.google.accompanist:accompanist-permissions:0.33.2-alpha")
//    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha04")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.7.0")


    implementation("com.google.accompanist:accompanist-flowlayout:0.24.8-beta")
    implementation("io.coil-kt:coil:2.2.2")

    //firebase
    implementation("com.google.android.gms:play-services-auth:21.0.0")


    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.navigation:navigation-compose:2.7.7")
}