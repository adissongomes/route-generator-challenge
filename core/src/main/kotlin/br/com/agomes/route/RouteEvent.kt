package br.com.agomes.route

import java.time.Instant
import java.util.UUID

data class RouteEvent(
    val id: UUID = UUID.randomUUID(),
    val routeId: UUID,
    val originId: UUID,
    val destinationId: UUID,
    val courierId: UUID,
    val status: RouteStatus,
    val eventTime: Instant
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RouteEvent

        if (routeId != other.routeId) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = routeId.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }

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
