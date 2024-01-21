package br.com.agomes.route

import br.com.agomes.route.dto.RouteEventDTO
import java.time.Instant
import java.util.UUID

fun newRouteEventDTO(): RouteEventDTO {
    return RouteEventDTO(
        id = UUID.randomUUID(),
        status = RouteStatus.CREATED.toString(),
        courierId = UUID.randomUUID(),
        originId = UUID.randomUUID(),
        destinationId = UUID.randomUUID(),
        eventTime = Instant.now(),
    )
}

fun newRouteEvent(): RouteEvent {
    return RouteEvent(
        id = UUID.randomUUID(),
        routeId = UUID.randomUUID(),
        status = RouteStatus.CREATED,
        eventTime = Instant.now(),
    )
}
