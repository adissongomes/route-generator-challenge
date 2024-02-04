package br.com.agomes.route.api

import br.com.agomes.route.dto.RouteDTO
import br.com.agomes.route.usecase.GetRouteUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/routes")
class RouteController(private val getRouteUseCase: GetRouteUseCase) {

    @GetMapping("/{id}")
    fun getRouteEvents(@PathVariable("id") id: UUID): RouteDTO {
        return getRouteUseCase.fetch(id)
    }
}