plugins {
    alias(libs.plugins.kotlin.jvm)
    `jacoco-report-aggregation`
}

allprojects {
    group = "br.com.agomes"
    version = "1.0.0"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "jacoco-report-aggregation")

    repositories {
        mavenCentral()
    }

    kotlin {
        jvmToolchain(17)
    }

    tasks {
        test {
            useJUnitPlatform()
        }
    }
}
