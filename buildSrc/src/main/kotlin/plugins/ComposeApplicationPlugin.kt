package plugins

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import extensions.addCompose

class ComposeApplicationPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        applyPlugins(target)
        // Get the ApplicationExtension and configure it
        target.extensions.configure(ApplicationExtension::class.java) {
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

    private fun Project.android(): ApplicationExtension {
        return extensions.getByType(ApplicationExtension::class.java)
    }
}