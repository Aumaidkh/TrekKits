import org.gradle.api.JavaVersion

object ProjectConfig {
    const val appId = "com.hopcape.trekkits"
    const val minSdk = 24
    const val compileSdk = 35
    const val targetSdk = 35
    const val versionCode = 1
    const val versionName = "1.0"
    const val packingResourceExcludes = "/META-INF/{AL2.0,LGPL2.1}"
    val jvm = Jvm
}

object Jvm {
    const val version = "21"
    val javaVersion = JavaVersion.VERSION_21
}