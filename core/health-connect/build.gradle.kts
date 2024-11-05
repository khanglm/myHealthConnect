plugins {
    alias(libs.plugins.mhc.android.library)
    alias(libs.plugins.mhc.hilt)
}

android {
    namespace = "vn.edu.hust.khanglm.myhealthconnect.healthconnect"
}

dependencies {

    implementation(project(":core:model"))

    implementation(libs.androidx.health.connect.api)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}