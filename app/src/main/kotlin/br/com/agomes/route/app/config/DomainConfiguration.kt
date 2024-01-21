package br.com.agomes.route.app.config

import br.com.agomes.route.repository.RouteRepository
import br.com.agomes.route.service.SaveRouteEventUseCaseService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class DomainConfiguration {

    @Bean
    fun saveRouteUseCase(routeRepository: RouteRepository) =
        SaveRouteEventUseCaseService(routeRepository)
}
