plugins {
    alias(libs.plugins.protobuf)
    alias(libs.plugins.buildlogic.dataStoreModule)
}

android {
    namespace = "com.hobbyloop.datastore"
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.dataStore.core)
    implementation(libs.protobuf.kotlin.lite)
}
