package br.com.agomes.route.usecase

import br.com.agomes.route.RouteEvent


fun interface SaveRouteEventUseCase {
    fun saveEvent(route: RouteEvent)
}