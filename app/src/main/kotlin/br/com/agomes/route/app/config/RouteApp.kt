package br.com.agomes.route.app.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["br.com.agomes.route"])
class RouteApp

fun main() {
    runApplication<RouteApp>()
}
