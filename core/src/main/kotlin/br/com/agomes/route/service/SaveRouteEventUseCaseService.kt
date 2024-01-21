package br.com.agomes.route.service

import br.com.agomes.route.RouteEvent
import br.com.agomes.route.exception.EntityAlreadyExistsException
import br.com.agomes.route.helper.loggerFor
import br.com.agomes.route.repository.RouteEventRepository
import br.com.agomes.route.usecase.SaveRouteEventUseCase

class SaveRouteEventUseCaseService(private val routeEventRepository: RouteEventRepository) : SaveRouteEventUseCase {

    override fun save(route: RouteEvent) {
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
        private val log = loggerFor<SaveRouteEventUseCaseService>()
    }
}
