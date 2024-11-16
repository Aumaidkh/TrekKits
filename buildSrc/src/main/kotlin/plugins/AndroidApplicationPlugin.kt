package plugins

import ProjectConfig
import com.android.build.api.dsl.ApplicationExtension
import extensions.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugins(this)
            setupProjectConfig(this)
            addDependencies(this)
        }
    }
    private fun applyPlugins(project: Project){
        with(project.pluginManager){
            apply("com.android.application")
            apply("kotlin-android")
        }
    }
    private fun setupProjectConfig(project: Project){
        project.android().apply{
            defaultConfig.apply {
                targetSdk = ProjectConfig.targetSdk
                applicationId = ProjectConfig.appId
                versionCode = ProjectConfig.versionCode
                versionName = ProjectConfig.versionName
            }

            compileOptions {
                sourceCompatibility = ProjectConfig.jvm.javaVersion
                targetCompatibility = ProjectConfig.jvm.javaVersion
            }

            packaging.resources.excludes += ProjectConfig.packingResourceExcludes
        }
    }
    private fun addDependencies(project: Project){
        with(project){
            dependencies {
                implementation("androidx-ktx",project)
                implementation("androidx-lifecycle-runtime-ktx",project)
                //implementation("junit",project)
                //implementation("androidx-junit",project)
            }
        }
    }

    private fun Project.android(): ApplicationExtension {
        return extensions.getByType(ApplicationExtension::class.java)
    }

}