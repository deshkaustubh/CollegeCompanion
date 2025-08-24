plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "tech.kaustubhdeshpande.collegecompanion"
    compileSdk = 35

    defaultConfig {
        applicationId = "tech.kaustubhdeshpande.collegecompanion"
        minSdk = 24
        targetSdk = 35
        versionCode = 3
        versionName = "1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    bundle {
        language {
            enableSplit = true
        }
        density {
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.foundation.android)
    implementation(libs.firebase.analytics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // material 3 icons
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // accompanist - to change status bar color
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    // splash screen implementation
    implementation("androidx.core:core-splashscreen:1.0.1")

    // nav controller
    implementation("androidx.navigation:navigation-compose:2.9.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Compose integration with ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // State handling and LiveData (optional if you're observing them)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    //exp4j for simple calculator
    implementation("net.objecthunter:exp4j:0.4.8")

    // for correcting the system bar and their icon colors
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")

    // Microsoft Clarity for Android
    implementation("com.microsoft.clarity:clarity-compose:3.4.5")
}