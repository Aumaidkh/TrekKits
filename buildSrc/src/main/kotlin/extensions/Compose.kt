package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addCompose(project: Project){
    implementation("androidx-activity-compose",project)
    implementation("androidx-core-ktx",project)
    implementation("androidx-lifecycle-runtime-ktx",project)
    implementation("androidx-material3",project)
    implementation("androidx-ui",project)
    implementation("androidx-ui-graphics",project)
    implementation("androidx-ui-tooling-preview",project)
    debugImplementation("androidx-ui-tooling",project)
    debugImplementation("androidx-ui-test-manifest",project)
    implementationPlatform("androidx-compose-bom",project)
}

fun DependencyHandlerScope.addCredentialsManager(project: Project){
    implementation("androidx-credentialsManager",project)
    implementation("androidx-credential-play-services-auth",project)
}

fun DependencyHandlerScope.addFirebase(project: Project){
    implementationPlatform("firebase-bom",project)
    implementation("firebase-auth",project)
    implementation("play-services-auth",project)
}

fun DependencyHandlerScope.addNavigation(project: Project){
    implementation("androidx-navigation-compose",project)
}

fun DependencyHandlerScope.addAuthModule(){
    addProject("auth")
}

fun DependencyHandlerScope.addDesignSystemModule(){
    addProject("designSystem")
}

fun DependencyHandlerScope.addCommonModule(){
    addProject("common")
}
