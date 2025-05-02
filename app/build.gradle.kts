plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.myproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myproject"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.okhttp)
    implementation (libs.volley)
    implementation (libs.gson)
    implementation (libs.circleimageview)
    implementation (libs.imageslideshow)
    implementation (libs.github.glide)
    implementation (libs.lottie)
    implementation("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")
    implementation ("com.github.dhaval2404:imagepicker:2.1")
    implementation ("com.razorpay:checkout:1.6.37")
    implementation ("com.github.transferwise:sequence-layout:1.2.0")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}