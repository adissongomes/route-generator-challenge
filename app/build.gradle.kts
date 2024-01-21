plugins {
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.boot.dependency.management)
}

dependencies {
    implementation(project(":core"))
    implementation(project(":infra"))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.actuator)
}
