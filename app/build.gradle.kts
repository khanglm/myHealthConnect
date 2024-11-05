import vn.edu.hust.khanglm.myhealthconnect.MhcBuildType

plugins {
    alias(libs.plugins.mhc.android.application)
    alias(libs.plugins.mhc.android.application.compose)
    alias(libs.plugins.mhc.android.application.flavors)
    alias(libs.plugins.mhc.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "vn.edu.hust.khanglm.myhealthconnect"

    defaultConfig {
        applicationId = "vn.edu.hust.khanglm.myhealthconnect"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = MhcBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":features:home"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:health-connect"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.androidx.health.connect.api)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

dependencyGuard {
    configuration("prodReleaseRuntimeClasspath")
}