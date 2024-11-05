plugins {
    alias(libs.plugins.mhc.android.library)
    alias(libs.plugins.mhc.hilt)
}

android {
    namespace = "vn.edu.hust.khanglm.myhealthconnect.common"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}