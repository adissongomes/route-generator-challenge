package br.com.agomes.route.service

import br.com.agomes.route.dto.RouteDTO
import br.com.agomes.route.dto.toDTO
import br.com.agomes.route.exception.EntityNotFoundException
import br.com.agomes.route.helper.loggerFor
import br.com.agomes.route.repository.RouteRepository
import br.com.agomes.route.usecase.GetRouteUseCase
import java.util.UUID

class GetRouteEventUseCaseService(private val routeRepository: RouteRepository) : GetRouteUseCase {
    override fun fetch(id: UUID): RouteDTO {
        log.info("Trying to fetch route with id: $id")
        return routeRepository.findById(id)?.toDTO() ?: throw EntityNotFoundException("Route with id $id not found")
    }

    companion object {
        private val log = loggerFor<GetRouteEventUseCaseService>()
    }
}
