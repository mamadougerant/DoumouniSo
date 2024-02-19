plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.malisoftware.components"
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

    // Accompanist
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")
    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")
    // icons
    implementation("androidx.compose.material:material-icons-extended:1.6.1")

    implementation(project(":data:model"))
    implementation(project(":common:theme"))
}