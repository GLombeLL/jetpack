

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.metosoft.jetpack3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.metosoft.jetpack3"
        minSdk = 28
        targetSdk = 34
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
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
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation ("androidx.compose.material:material-icons-extended")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
        implementation ("androidx.activity:activity-compose:1.3.1")
        implementation ("androidx.compose.ui:ui:1.0.1")
        implementation ("androidx.compose.material:material:1.0.1")
        implementation ("androidx.compose.ui:ui-tooling-preview:1.0.1")
        implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
        implementation ("androidx.navigation:navigation-compose:2.4.0-alpha10")

    //implementation (libs.jetpack3)
    // implementation ("libs/jetpack3-1.0.0.jar")

}
/*repositories {
    google()
    mavenCentral()
    maven {
        //url = https://maven.jetpack3.com
    }

}*/



