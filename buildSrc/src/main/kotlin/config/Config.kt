package config

import org.gradle.api.JavaVersion

object Config {
    val android = AndroidConfig(
        minSdkVersion = 24,
        targetSdkVersion = 35,
        compileSdkVersion = 35,
        applicationId = "com.hopcape.trekkits",
        versionCode = 1,
        versionName = "1.0",
        nameSpace = "com.hopcape.trekkits"
    )
    val jvm = JvmConfig(
        javaVersion = JavaVersion.VERSION_11,
        kotlinJvm = JavaVersion.VERSION_11.toString(),
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    )
}
data class AndroidConfig(
    val minSdkVersion : Int,
    val targetSdkVersion : Int,
    val compileSdkVersion : Int,
    val applicationId : String,
    val versionCode : Int,
    val versionName : String,
    val nameSpace: String
)
data class JvmConfig(
    val javaVersion : JavaVersion,
    val kotlinJvm : String,
    val freeCompilerArgs : List<String>
)