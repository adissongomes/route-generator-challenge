package br.com.agomes.route.infra.data

import br.com.agomes.route.RouteEvent
import br.com.agomes.route.RouteStatus
import br.com.agomes.route.repository.RouteEventRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.UUID

@Repository
class RouteEventJdbcRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : RouteEventRepository {

    private val rowMapper = RouteEventRowMapper()

    override fun save(routeEvent: RouteEvent) {
        val sql = """
            INSERT INTO route_events (id, route_id, origin_id, destination_id, courier_id, status, event_time)
            VALUES (:id, :route_id, :origin_id, :destination_id, :courier_id, :status, :event_time)
        """.trimIndent()

        jdbcTemplate.update(sql, routeEvent.toParams())
    }


    override fun findByRouteIdAndStatus(routeId: UUID, status: RouteStatus): RouteEvent? {
        val sql = "SELECT * FROM route_events WHERE route_id = :route_id AND status = :status"

        val params = mapOf("route_id" to routeId, "status" to status.name)

        return jdbcTemplate.query(sql, params, rowMapper).firstOrNull()
    }
}

private fun RouteEvent.toParams(): Map<String, Any> = mapOf(
    "id" to id,
    "route_id" to routeId,
    "origin_id" to originId,
    "destination_id" to destinationId,
    "courier_id" to courierId,
    "status" to status.name,
    "event_time" to Timestamp.from(eventTime)
)
