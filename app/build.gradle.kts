plugins {
    alias(libs.plugins.androidApplication)
}
android {
    namespace = Define.APPLICATION_ID
    compileSdk = Define.TARGET_SDK

    defaultConfig {
        applicationId = Define.APPLICATION_ID
        minSdk = Define.MIN_SDK
        targetSdk = 34
        versionCode = Define.VERSION_CODE
        versionName = Define.VERSION_NAME

 /*       testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"*/
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.orhanobut.logger)

}