package br.com.agomes.route.api

import br.com.agomes.route.AppTestConfiguration
import br.com.agomes.route.Route
import br.com.agomes.route.RouteEvent
import br.com.agomes.route.RouteStatus
import br.com.agomes.route.repository.RouteRepository
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.UUID

@SpringBootTest
@Import(AppTestConfiguration::class)
@AutoConfigureMockMvc
@Transactional
class RouteControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var repository: RouteRepository

    @Test
    fun `should fetch a route with its events`() {
        val id = UUID.randomUUID()
        repository.save(newRoute(id))

        mockMvc.perform(MockMvcRequestBuilders.get("/routes/{id}", id))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.events.length()", `is`(2)))
    }

    @Test
    fun `should return not found when id does not exists`() {
        val id = UUID.randomUUID()

        mockMvc.perform(MockMvcRequestBuilders.get("/routes/{id}", id))
            .andExpect(status().isNotFound)
    }

    private fun newRoute(routeId: UUID = UUID.randomUUID()): Route {
        return Route(
            id = routeId,
            originId = UUID.randomUUID(),
            destinationId = UUID.randomUUID(),
            courierId = UUID.randomUUID(),
            status = RouteStatus.CANCELED,
            events = listOf(
                RouteEvent(
                    id = UUID.randomUUID(),
                    routeId = routeId,
                    status = RouteStatus.CREATED,
                    eventTime = Instant.now().minusSeconds(600)
                ),
                RouteEvent(
                    id = UUID.randomUUID(),
                    routeId = routeId,
                    status = RouteStatus.CANCELED,
                    eventTime = Instant.now()
                ),
            )
        )
    }
}
