package br.com.agomes.route.dto

import br.com.agomes.route.RouteEvent
import java.time.Instant
import java.util.UUID

// TODO move to infrastructure module
data class RouteEventDTO(
    val id: UUID,
    val originId: UUID,
    val destinationId: UUID,
    val courierId: UUID,
    val status: String,
    val eventTime: Instant
)

fun RouteEventDTO.toRoute(): RouteEvent {
    return RouteEvent(
        routeId = id,
        originId = originId,
        destinationId = destinationId,
        courierId = courierId,
        status = enumValueOf(status),
        eventTime = eventTime
    )
}
