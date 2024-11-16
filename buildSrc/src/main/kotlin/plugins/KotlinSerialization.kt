package plugins

import extensions.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSerialization : Plugin<Project>{
    override fun apply(target: Project) {
        with(target){
            // Apply Plugin
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            // Add Dependency
            dependencies {
                implementation("kotlinx-serialization-json",project)
            }
        }
    }

}