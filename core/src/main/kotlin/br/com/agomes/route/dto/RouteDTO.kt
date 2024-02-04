package br.com.agomes.route.dto

import br.com.agomes.route.Route
import br.com.agomes.route.RouteStatus
import java.time.Instant

data class RouteDTO(
    val id: String,
    val originId: String,
    val destinationId: String,
    val courierId: String,
    val status: String,
    val events: Map<RouteStatus, Instant>,
)

fun Route.toDTO() = RouteDTO(
    id = id.toString(),
    originId = originId.toString(),
    destinationId = destinationId.toString(),
    courierId = courierId.toString(),
    status = status.name,
    events = events.associate { it.status to it.eventTime }
)
