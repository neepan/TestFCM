import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.neepan.testfcm"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }
    val localProperties = Properties()
    val localPropertiesFile = File(rootDir,"secret.properties")
    if(localPropertiesFile.exists() && localPropertiesFile.isFile){
        localPropertiesFile.inputStream().use{
            localProperties.load(it)
        }
    }

    defaultConfig {
        applicationId = "com.neepan.testfcm"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "SUPABASE_URL",
            "\"${localProperties.getProperty("supabaseUrl")}\""
        )
        buildConfigField(
            "String",
            "SUPABASE_KEY",
            "\"${localProperties.getProperty("supabaseKey")}\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug{

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig= true
        resValues= true
    }
}

dependencies {
    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.9.7")

    //supabase
    implementation(platform("io.github.jan-tennert.supabase:bom:3.3.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.github.jan-tennert.supabase:realtime-kt")
    implementation("io.github.jan-tennert.supabase:functions-kt")
    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.2.0")

    //ktor
    implementation("io.ktor:ktor-client-core:3.3.1")
    implementation("io.ktor:ktor-client-okhttp:3.3.1")
    implementation("io.ktor:ktor-client-logging:3.3.1")
    implementation("io.ktor:ktor-client-cio:3.3.1")
    implementation("io.ktor:ktor-client-content-negotiation:3.3.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.1")
    implementation("io.ktor:ktor-client-auth:3.3.1")
    implementation("com.squareup.okhttp3:okhttp:5.3.2")
    implementation("org.slf4j:slf4j-simple:2.0.17")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")

    // Camera
    implementation("androidx.camera:camera-camera2:1.5.3")
    implementation("androidx.camera:camera-lifecycle:1.5.3")
    implementation("androidx.camera:camera-view:1.5.3")

    // ML Kit Barcode Scanning
    implementation("com.google.mlkit:barcode-scanning:17.3.0")

    // Guava ListenableFuture support for CameraX
    implementation("com.google.guava:guava:33.5.0-android")

    // Accompanist Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.36.0")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}