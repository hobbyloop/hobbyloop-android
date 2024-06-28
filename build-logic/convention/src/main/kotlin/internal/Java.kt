package internal

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension

internal fun Project.configureJava() {

    val javaPluginExtension = extensions.findByType(JavaPluginExtension::class.java)
    javaPluginExtension?.sourceCompatibility = JavaVersion.VERSION_17
    javaPluginExtension?.targetCompatibility = JavaVersion.VERSION_17
}
