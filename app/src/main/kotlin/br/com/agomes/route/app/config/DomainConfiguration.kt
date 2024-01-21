package br.com.agomes.route.app.config

import br.com.agomes.route.infra.data.RouteEventJdbcRepository
import br.com.agomes.route.repository.RouteEventRepository
import br.com.agomes.route.service.SaveRouteEventUseCaseService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration(proxyBeanMethods = false)
class DomainConfiguration {

    @Bean
    fun routeEventRepository(jdbcTemplate: NamedParameterJdbcTemplate): RouteEventRepository =
        RouteEventJdbcRepository(jdbcTemplate)

    @Bean
    fun saveRouteUseCase(routeEventRepository: RouteEventRepository) =
        SaveRouteEventUseCaseService(routeEventRepository)
}
