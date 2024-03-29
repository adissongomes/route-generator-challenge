plugins {
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.boot.dependency.management)
}

dependencies {
    implementation(project(":core"))
    implementation(libs.spring.boot.starter.kafka)
    api(libs.spring.boot.starter.jdbc)
    implementation(libs.postgres)
    implementation(libs.jackson.kotlin)
    testImplementation(libs.bundles.unit.test)
}
