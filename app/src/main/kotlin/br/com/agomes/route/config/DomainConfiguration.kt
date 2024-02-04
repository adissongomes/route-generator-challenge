package br.com.agomes.route.config

import br.com.agomes.route.repository.RouteRepository
import br.com.agomes.route.service.GetRouteEventUseCaseService
import br.com.agomes.route.service.SaveRouteEventUseCaseService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class DomainConfiguration(private val routeRepository: RouteRepository) {

    @Bean
    fun saveRouteUseCase() =
        SaveRouteEventUseCaseService(routeRepository)

    @Bean
    fun getRouteUseCase() =
        GetRouteEventUseCaseService(routeRepository)
}
