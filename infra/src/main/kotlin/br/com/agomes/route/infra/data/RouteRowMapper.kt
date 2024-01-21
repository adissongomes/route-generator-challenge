package br.com.agomes.route.infra.data

import br.com.agomes.route.Route
import org.springframework.jdbc.core.RowMapper
import java.util.UUID

class RouteRowMapper : RowMapper<Route> {

    override fun mapRow(rs: java.sql.ResultSet, rowNum: Int): Route {
        return Route(
            id = UUID.fromString(rs.getString("id")),
            originId = UUID.fromString(rs.getString("origin_id")),
            destinationId = UUID.fromString(rs.getString("destination_id")),
            courierId = UUID.fromString(rs.getString("courier_id")),
            status = enumValueOf(rs.getString("status")),
            createdAt = rs.getTimestamp("created_at").toInstant(),
            updatedAt = rs.getTimestamp("updated_at").toInstant(),
        )
    }
}
