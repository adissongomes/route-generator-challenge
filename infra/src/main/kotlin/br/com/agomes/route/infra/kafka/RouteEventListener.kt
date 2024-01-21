package br.com.agomes.route.infra.kafka

import br.com.agomes.route.dto.RouteEventDTO
import br.com.agomes.route.exception.EntityAlreadyExistsException
import br.com.agomes.route.helper.loggerFor
import br.com.agomes.route.usecase.SaveRouteEventUseCase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class RouteEventListener(private val saveRouteEventUseCase: SaveRouteEventUseCase) {

    @KafkaListener(topics = ["\${kafka.topic.route}"])
    fun handle(routeEventDTO: RouteEventDTO) {
        try {
            saveRouteEventUseCase.save(routeEventDTO)
        } catch (e: EntityAlreadyExistsException) {
            log.warn(e.message)
        }
    }

    companion object {
        private val log = loggerFor<RouteEventListener>()
    }
}
