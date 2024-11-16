package plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import extensions.addCompose

class ComposeLibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        applyPlugins(target)
        target.extensions.getByType(LibraryExtension::class.java).apply {
            buildFeatures {
                compose = true
            }
        }
        addDependencies(target)

    }

    private fun addDependencies(project: Project){
        with(project){
            dependencies {
                addCompose(project)
            }
        }
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin("org.jetbrains.kotlin.plugin.compose")
        }
    }
}