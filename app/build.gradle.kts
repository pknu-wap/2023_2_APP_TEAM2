
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.gogo"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.gogo"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }
//    kapt {
//        arguments {
//            arg("room.schemaLocation", "$projectDir/schemas")
//        }
//    }

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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.3.1")

    implementation("androidx.databinding:databinding-runtime:8.1.4")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    val room_version = "2.6.0"

    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    implementation("com.google.android.material:material:1.10.0")

    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.activity:activity:1.8.0")

    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.fragment:fragment:1.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")



    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("androidx.recyclerview:recyclerview:1.3.2")

    implementation("de.hdodenhof:circleimageview:3.1.0")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation("com.github.bumptech.glide:glide:4.16.0")

    implementation("androidx.camera:camera-core:1.1.0-beta01")
    implementation("androidx.camera:camera-camera2:1.1.0-beta01")
    implementation("androidx.camera:camera-lifecycle:1.1.0-beta01")
    implementation("androidx.camera:camera-video:1.1.0-beta01")
    implementation("androidx.camera:camera-view:1.1.0-beta01")
    implementation("androidx.camera:camera-extensions:1.1.0-beta01")

}