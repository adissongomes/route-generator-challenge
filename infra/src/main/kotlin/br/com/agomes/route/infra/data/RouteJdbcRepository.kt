package br.com.agomes.route.infra.data

import br.com.agomes.route.Route
import br.com.agomes.route.RouteEvent
import br.com.agomes.route.RouteStatus
import br.com.agomes.route.repository.RouteRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.UUID

@Repository
class RouteJdbcRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : RouteRepository {

    private val routeRowMapper = RouteRowMapper()
    private val routeEventRowMapper = RouteEventRowMapper()

    override fun save(route: Route) {
        val count =
            jdbcTemplate.queryForObject(
                "SELECT count(id) FROM routes WHERE id = :id",
                mapOf("id" to route.id),
                Int::class.java
            )

        val sql = if (count == 0) {
            insertRouteSql
        } else {
            updateRouteSql
        }

        jdbcTemplate.update(sql, route.toParams())
        route.events.forEach(::saveEvent)
    }

    override fun findById(id: UUID): Route? {
        val sql = "SELECT * FROM routes WHERE id = :id"

        val params = mapOf("id" to id)

        val route = jdbcTemplate.query(sql, params, routeRowMapper).firstOrNull()?.apply {
            events = findEventsByRouteId(id)
        }
        return route
    }

    override fun saveEvent(routeEvent: RouteEvent) {
        jdbcTemplate.update(insertEventSql, routeEvent.toParams())
    }

    override fun findByRouteIdAndStatus(routeId: UUID, status: RouteStatus): RouteEvent? {
        val sql = "SELECT * FROM route_events WHERE route_id = :route_id AND status = :status"

        val params = mapOf("route_id" to routeId, "status" to status.name)

        return jdbcTemplate.query(sql, params, routeEventRowMapper).firstOrNull()
    }

    private fun findEventsByRouteId(routeId: UUID): List<RouteEvent> {
        val sql = "SELECT * FROM route_events WHERE route_id = :route_id"

        val params = mapOf("route_id" to routeId)

        return jdbcTemplate.query(sql, params, routeEventRowMapper)
    }

    companion object {
        private val insertRouteSql = """
            INSERT INTO routes (id, origin_id, destination_id, courier_id, status, created_at)
            VALUES (:id, :origin_id, :destination_id, :courier_id, :status, :created_at)
        """.trimIndent()

        private val updateRouteSql = "UPDATE routes SET status = :status, updated_at = now() WHERE id = :id"

        private val insertEventSql = """
            INSERT INTO route_events (id, route_id, status, event_time)
            VALUES (:id, :route_id, :status, :event_time)
        """.trimIndent()
    }
}

private fun Route.toParams(): Map<String, Any> = mapOf(
    "id" to id,
    "origin_id" to originId,
    "destination_id" to destinationId,
    "courier_id" to courierId,
    "status" to status.name,
    "created_at" to Timestamp.from(createdAt),
    "updated_at" to Timestamp.from(updatedAt)
)

private fun RouteEvent.toParams(): Map<String, Any> = mapOf(
    "id" to id,
    "route_id" to routeId,
    "status" to status.name,
    "event_time" to Timestamp.from(eventTime)
)
