plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3").version("3.8.1")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.data"
    compileSdk = 33

    defaultConfig {
        buildConfigField(
            "String",
            "ANILIST_API__URL",
            project.property("AniListApiUrl").toString()
        )
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation (project(":domain"))
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    testImplementation(libs.junit)
    testImplementation("org.testng:testng:6.9.6")
    androidTestImplementation(libs.androidxTestExtJUnit)
    androidTestImplementation(libs.espressoCore)
    implementation(libs.apollo.runtime)
    implementation(libs.okhttp)
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)
    implementation(libs.bundles.paging)
    implementation(libs.bundles.room)
    ksp(libs.roomCompiler)

}

kapt {
    correctErrorTypes = true
}

apollo {
    service("service") {
        packageName.set("com.example.data")
    }
}