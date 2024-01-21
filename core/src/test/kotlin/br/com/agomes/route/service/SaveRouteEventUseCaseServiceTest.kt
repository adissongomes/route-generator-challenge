package br.com.agomes.route.service

import br.com.agomes.route.Route
import br.com.agomes.route.dto.toRouteEvent
import br.com.agomes.route.exception.EntityAlreadyExistsException
import br.com.agomes.route.newRouteEventDTO
import br.com.agomes.route.repository.RouteRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SaveRouteEventUseCaseServiceTest {

    @MockK
    private lateinit var routeRepository: RouteRepository

    @InjectMockKs
    private lateinit var service: SaveRouteEventUseCaseService

    @Test
    fun `should save event`() {
        val slot = slot<Route>()
        every { routeRepository.findByRouteIdAndStatus(any(), any()) } returns null
        justRun { routeRepository.save(capture(slot)) }

        val route = newRouteEventDTO()
        service.save(route)

        val capturedRoute = slot.captured
        assertThat(capturedRoute.id).isEqualTo(route.id)
        assertThat(capturedRoute.status.toString()).isEqualTo(route.status)
        assertThat(capturedRoute.originId).isEqualTo(route.originId)
        assertThat(capturedRoute.destinationId).isEqualTo(route.destinationId)
        assertThat(capturedRoute.courierId).isEqualTo(route.courierId)
        assertThat(capturedRoute.events).hasSize(1)
    }

    @Test
    fun `should not save event when event already exists`() {
        val route = newRouteEventDTO()
        every { routeRepository.findByRouteIdAndStatus(any(), any()) } returns route.toRouteEvent()

        assertThrows<EntityAlreadyExistsException> { service.save(route) }

        verify(exactly = 0) { routeRepository.saveEvent(any()) }
    }
}
