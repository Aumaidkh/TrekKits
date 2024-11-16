package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addHilt(project: Project){
    implementation("hilt-android",project)
    kapt("hilt-compiler",project)
}