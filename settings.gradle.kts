plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "route-generator-challenge"
include("core", "infra", "app")
