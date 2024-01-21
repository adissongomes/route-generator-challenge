package br.com.agomes.route.service

import br.com.agomes.route.dto.RouteEventDTO
import br.com.agomes.route.dto.toRoute
import br.com.agomes.route.exception.EntityAlreadyExistsException
import br.com.agomes.route.helper.loggerFor
import br.com.agomes.route.repository.RouteRepository
import br.com.agomes.route.usecase.SaveRouteEventUseCase

class SaveRouteEventUseCaseService(private val routeRepository: RouteRepository) : SaveRouteEventUseCase {

    override fun save(routeEvent: RouteEventDTO) {
        isValid(routeEvent)
        routeRepository.save(routeEvent.toRoute())
        log.info("Event saved: $routeEvent")
    }

    private fun isValid(route: RouteEventDTO) {
        val eventFound = routeRepository.findByRouteIdAndStatus(route.id, enumValueOf(route.status))
        if (eventFound != null) {
            throw EntityAlreadyExistsException("$eventFound already exists")
        }
    }

    companion object {
        private val log = loggerFor<SaveRouteEventUseCaseService>()
    }
}
