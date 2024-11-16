package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import extensions.addHilt

class HiltPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target){
            target.pluginManager.apply {
                apply("dagger.hilt.android.plugin")
                apply("kotlin-kapt")
            }

            dependencies {
                addHilt(project)
            }
        }
    }
}