dependencies {
    implementation(libs.slf4j.api)
    testImplementation(libs.bundles.unit.test)
    testRuntimeOnly(libs.slf4j.jdk)
}
