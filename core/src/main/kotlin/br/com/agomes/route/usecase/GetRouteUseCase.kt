package br.com.agomes.route.usecase

import br.com.agomes.route.dto.RouteDTO
import java.util.UUID

fun interface GetRouteUseCase {
    fun fetch(id: UUID): RouteDTO
}
