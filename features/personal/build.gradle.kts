plugins {
    alias(libs.plugins.mhc.android.feature)
    alias(libs.plugins.mhc.android.library.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.mhc.hilt)
}

android {
    namespace = "vn.edu.hust.khanglm.features.personal"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}