package br.com.agomes.route.repository

import br.com.agomes.route.Route
import br.com.agomes.route.RouteEvent
import br.com.agomes.route.RouteStatus
import java.util.UUID

interface RouteRepository {
    fun save(route: Route)
    fun saveEvent(routeEvent: RouteEvent)
    fun findByRouteIdAndStatus(routeId: UUID, status: RouteStatus): RouteEvent?
    fun findById(id: UUID): Route?
}
