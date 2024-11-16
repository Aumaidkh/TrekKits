import extensions.implementation

plugins {
    id("tt.android.library")
    id("tt.compose.library")
    id("tt.serialization")
}


android {
    namespace = "com.hopcape.auth"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}