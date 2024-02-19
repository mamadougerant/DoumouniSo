plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("io.realm.kotlin")
}

android {
    namespace = "com.malisoftware.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    composeCommon()

    // Dagger - Hilt
    daggerHilt()

    implementation(project(":common:components"))
    implementation(project(":common:theme"))
    implementation(project(":data:model"))
    implementation(project(":future:search"))
    implementation(project(":common:futureapi"))
    implementation(project(":data:local"))
    implementation(project(":data:backend"))
    implementation(project(":ai"))

    implementation ("io.realm.kotlin:library-base:1.13.0")
    //implementation ("io.realm.kotlin:library-sync:1.11.0")// If using Device Sync
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}