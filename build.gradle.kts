plugins {
    alias(libs.plugins.kotlin.jvm)
}

allprojects {
    group = "br.com.agomes"
    version = "1.0.0"

    apply(plugin = "org.jetbrains.kotlin.jvm")

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
