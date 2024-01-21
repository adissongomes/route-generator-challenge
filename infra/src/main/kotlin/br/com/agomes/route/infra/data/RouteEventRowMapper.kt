package br.com.agomes.route.infra.data

import br.com.agomes.route.RouteEvent
import org.springframework.jdbc.core.RowMapper
import java.util.UUID

class RouteEventRowMapper : RowMapper<RouteEvent> {

    override fun mapRow(rs: java.sql.ResultSet, rowNum: Int): RouteEvent {
        return RouteEvent(
            id = UUID.fromString(rs.getString("id")),
            routeId = UUID.fromString(rs.getString("route_id")),
            status = enumValueOf(rs.getString("status")),
            eventTime = rs.getTimestamp("event_time").toInstant()
        )
    }
}
