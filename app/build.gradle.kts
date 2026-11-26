plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "jp.os41.compose"
    compileSdk = 35

    defaultConfig {
        applicationId = "jp.os41.compose"
        minSdk = 29
        targetSdk = 35
        // CI ではビルド番号を versionCode に使い、上書きインストールできるようにする
        versionCode = (System.getenv("GITHUB_RUN_NUMBER") ?: "1").toInt()
        versionName = "1.0." + (System.getenv("GITHUB_RUN_NUMBER") ?: "0")
    }

    signingConfigs {
        // 個人での配布用の固定鍵。毎回同じ鍵で署名するので、データを残したまま上書き更新できる
        create("fixed") {
            storeFile = file("signing.keystore")
            storePassword = "composeassist"
            keyAlias = "compose"
            keyPassword = "composeassist"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("fixed")
        }
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("fixed")
        }
    }

    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("androidx.webkit:webkit:1.12.1")
}
