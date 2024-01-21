package br.com.agomes.route.service

import br.com.agomes.route.RouteEvent
import br.com.agomes.route.exception.EntityAlreadyExistsException
import br.com.agomes.route.repository.RouteEventRepository
import br.com.agomes.route.usecase.SaveRouteEventUseCase
import org.slf4j.LoggerFactory

class SaveRouteUseCaseService(private val routeEventRepository: RouteEventRepository) : SaveRouteEventUseCase {

    override fun saveEvent(route: RouteEvent) {
        isValid(route)
        routeEventRepository.save(route)
        log.info("Event saved: $route")
    }

    private fun isValid(route: RouteEvent) {
        val eventFound = routeEventRepository.findByRouteIdAndStatus(route.routeId, route.status)
        if (eventFound != null) {
            throw EntityAlreadyExistsException("$eventFound already exists")
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(SaveRouteUseCaseService::class.java)
    }
}
