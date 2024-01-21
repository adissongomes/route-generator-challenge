package br.com.agomes.route

import java.time.Instant
import java.util.UUID

fun newRouteEvent(): RouteEvent {
    return RouteEvent(
        routeId = UUID.randomUUID(),
        status = RouteStatus.CREATED,
        courierId = UUID.randomUUID(),
        originId = UUID.randomUUID(),
        destinationId = UUID.randomUUID(),
        eventTime = Instant.now(),
    )
}
