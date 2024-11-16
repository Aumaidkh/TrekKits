//package extensions
//
//import com.android.build.api.dsl.CommonExtension
//import com.android.build.api.dsl.BuildType
//import com.android.build.api.dsl.DefaultConfig
//import com.android.build.api.dsl.ProductFlavor
//import com.android.build.api.dsl.AndroidResources
//import org.gradle.api.Project
//
//fun Project.configureBuildTypes(
//    commonExtension: CommonExtension<*, BuildType, DefaultConfig, ProductFlavor, AndroidResources>
//) {
//    commonExtension.apply {
//        buildTypes {
//            getByName("release"){
//                isMinifyEnabled = false
//                proguardFiles(
//                    getDefaultProguardFile("proguard-android-optimize.txt"),
//                    "proguard-rules.pro"
//                )
//            }
//        }
//    }
//}