package br.com.agomes.route.usecase

import br.com.agomes.route.dto.RouteEventDTO


fun interface SaveRouteEventUseCase {
    fun save(routeEvent: RouteEventDTO)
}
