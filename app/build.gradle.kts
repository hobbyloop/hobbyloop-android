plugins {
    id("buildlogic.appmodule")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:center"))
    implementation(project(":feature:center-detail"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:my-page"))
    implementation(project(":feature:reservation"))
    implementation(project(":feature:reservation-detail"))
    implementation(project(":feature:sign-up"))
    implementation(project(":feature:schedule"))
    implementation("com.kakao.sdk:v2-user:2.12.1")
}
