plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // NUEVO: Plugin de Compose Compiler (Requerido para Kotlin 2.0+)
    id("org.jetbrains.kotlin.plugin.compose")
    // ACTUALIZADO: KSP debe ser compatible con Kotlin 2.0 (versión 2.0.0-1.0.21 o superior)
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
}

android {
    namespace = "com.example.appmedidores"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.appmedidores"
        minSdk = 24
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
    // NOTA: Hemos eliminado el bloque 'composeOptions' porque ya no se necesita en Kotlin 2.0
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // --- Core de Android y Compose ---
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // --- TUS DEPENDENCIAS (Navegación + Room + ViewModel) ---
    val navVersion = "2.7.7"
    val roomVersion = "2.6.1"

    // Navegación
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Room (Base de datos)
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion") // Corrutinas para Room
    ksp("androidx.room:room-compiler:$roomVersion")      // Procesador (KSP)

    implementation("androidx.compose.material:material-icons-extended")

    // --- Testing ---
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}