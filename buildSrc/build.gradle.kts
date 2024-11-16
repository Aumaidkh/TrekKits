import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.7.2")
    implementation("com.squareup:javapoet:1.13.0")
}

gradlePlugin{
    plugins{
        register("androidLibrary"){
            id="tt.android.library"
            implementationClass="plugins.AndroidLibraryPlugin"
        }
        register("hiltPlugin"){
            id="tt.hilt"
            implementationClass="plugins.HiltPlugin"
        }
        register("composePlugin"){
            id="tt.compose.library"
            implementationClass="plugins.ComposeLibraryPlugin"
        }
        register("composeAppPlugin"){
            id="tt.compose.app"
            implementationClass="plugins.ComposeApplicationPlugin"
        }

        register("applicationPlugin"){
            id="tt.application"
            implementationClass="plugins.AndroidApplicationPlugin"
        }

        register("kotlinSerialization"){
            id="tt.serialization"
            implementationClass="plugins.KotlinSerialization"
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}