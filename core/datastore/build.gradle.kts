plugins {
    alias(libs.plugins.mhc.android.library)
    alias(libs.plugins.mhc.hilt)
}

android {
    namespace = "vn.edu.hust.khanglm.core.datastore"
}

dependencies {

    implementation(libs.androidx.dataStore)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.androidx.datastore.preferences)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}