package br.com.agomes.route.infra.config

import br.com.agomes.route.RouteEvent
import br.com.agomes.route.RouteStatus
import br.com.agomes.route.repository.RouteEventRepository
import br.com.agomes.route.service.SaveRouteEventUseCaseService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.UUID

@Configuration
class DomainConfiguration {

    @Bean
    fun routeRepository() = object : RouteEventRepository {
        override fun save(routeEvent: RouteEvent) {
            TODO("Not yet implemented")
        }

        override fun findByRouteIdAndStatus(routeId: UUID, status: RouteStatus): RouteEvent? {
            TODO("Not yet implemented")
        }
    }

    @Bean
    fun saveRouteUseCase() = SaveRouteEventUseCaseService(routeRepository())
}
