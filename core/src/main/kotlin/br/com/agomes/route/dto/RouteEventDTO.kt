package br.com.agomes.route.dto

import br.com.agomes.route.Route
import br.com.agomes.route.RouteEvent
import br.com.agomes.route.RouteStatus
import java.time.Instant
import java.util.UUID

data class RouteEventDTO(
    val id: UUID,
    val originId: UUID,
    val destinationId: UUID,
    val courierId: UUID,
    val status: String,
    val eventTime: Instant
)

fun RouteEventDTO.toRouteEvent(): RouteEvent {
    val status = RouteStatus.valueOf(status)
    return RouteEvent(
        id = id,
        routeId = id,
        status = status,
        eventTime = eventTime
    )
}

fun RouteEventDTO.toRoute(): Route {
    val status = RouteStatus.valueOf(status)
    val event = RouteEvent(routeId = id, status = status, eventTime = eventTime)
    return Route(
        id = id,
        originId = originId,
        destinationId = destinationId,
        courierId = courierId,
        status = status,
        events = listOf(event)
    )
}
