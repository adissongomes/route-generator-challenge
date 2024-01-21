package br.com.agomes.route.repository

import br.com.agomes.route.RouteEvent
import br.com.agomes.route.RouteStatus
import java.util.UUID

interface RouteEventRepository {
    fun save(routeEvent: RouteEvent)
    fun findByRouteIdAndStatus(routeId: UUID, status: RouteStatus): RouteEvent?
}
