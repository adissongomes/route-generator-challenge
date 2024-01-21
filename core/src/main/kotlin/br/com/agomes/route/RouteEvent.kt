package br.com.agomes.route

import java.time.Instant
import java.util.UUID

data class RouteEvent(
    val id: UUID = UUID.randomUUID(),
    val routeId: UUID,
    val status: RouteStatus,
    val eventTime: Instant
) {
    override fun toString(): String {
        return "RouteEvent(routeId=$routeId, status=$status)"
    }
}

enum class RouteStatus {
    CREATED,
    WAITING_COURIER,
    ACCEPTED,
    STARTED,
    COMPLETED,
    CANCELED
}
