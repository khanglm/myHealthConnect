plugins {
    alias(libs.plugins.mhc.android.library)
    alias(libs.plugins.mhc.hilt)
}

android {
    namespace = "vn.edu.hust.khanglm.myhealthconnect.core.data"
}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:health-connect"))
    implementation(project(":core:common"))

    implementation(libs.androidx.work.manager)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}