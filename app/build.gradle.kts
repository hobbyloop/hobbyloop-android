plugins {
    id("buildlogic.appmodule")
}

android {
    namespace = "com.hobbyloop.member"
}

dependencies {
    implementation(project(":feature:login"))
    implementation(project(":feature:signup"))
    implementation(project(":feature:home"))
    implementation(project(":feature:facility"))
    implementation(project(":feature:facility-card"))
    implementation(project(":feature:reservation"))
    implementation(project(":feature:storage"))
    implementation(project(":feature:my-page"))
}
