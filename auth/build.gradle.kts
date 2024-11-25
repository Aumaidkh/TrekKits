import extensions.addCommonModule
import extensions.addCredentialsManager
import extensions.addDesignSystemModule
import extensions.addFirebase

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
    implementation(libs.googleid)
    addFirebase(project)
    addCredentialsManager(project)
    addDesignSystemModule()
    addCommonModule()
    implementation(libs.androidx.navigation.compose)
}