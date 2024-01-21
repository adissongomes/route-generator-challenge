package br.com.agomes.route

import java.time.Instant
import java.util.UUID

data class Route(
    val id: UUID,
    val originId: UUID,
    val destinationId: UUID,
    val courierId: UUID,
    val status: RouteStatus,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
    var events: List<RouteEvent> = emptyList()
)
