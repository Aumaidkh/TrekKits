import extensions.addDesignSystemModule

plugins {
    id("tt.android.library")
    id("tt.compose.library")
    id("tt.serialization")
    id("tt.hilt")
}


android {
    namespace = "com.hopcape.auth"
}

dependencies {
    addDesignSystemModule()
    implementation(libs.androidx.navigation.compose)
}