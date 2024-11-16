package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope


fun DependencyHandlerScope.implementation(dependency: String,project: Project) {
    add("implementation",project.versionCatalog().findLibrary(dependency).get())
}

fun DependencyHandlerScope.debugImplementation(dependency: String,project: Project) {
    add("debugImplementation",project.versionCatalog().findLibrary(dependency).get())
}

fun DependencyHandlerScope.implementationPlatform(dependency: String,project: Project) {
    add("implementation",platform(project.versionCatalog().findLibrary(dependency).get()))
}

fun DependencyHandlerScope.kapt(dependency: String, project: Project){
    add("kapt",project.versionCatalog().findLibrary(dependency).get())
}
