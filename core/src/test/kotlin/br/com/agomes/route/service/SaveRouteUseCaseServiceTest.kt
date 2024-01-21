package br.com.agomes.route.service

import br.com.agomes.route.RouteEvent
import br.com.agomes.route.exception.EntityAlreadyExistsException
import br.com.agomes.route.newRouteEvent
import br.com.agomes.route.repository.RouteEventRepository
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
class SaveRouteUseCaseServiceTest {

    @MockK
    private lateinit var routeEventRepository: RouteEventRepository

    @InjectMockKs
    private lateinit var service: SaveRouteUseCaseService

    @Test
    fun `should save event`() {
        val slot = slot<RouteEvent>()
        every { routeEventRepository.findByRouteIdAndStatus(any(), any()) } returns null
        justRun { routeEventRepository.save(capture(slot)) }

        val route = newRouteEvent()
        service.saveEvent(route)

        assertThat(slot.captured).isEqualTo(route)
    }

    @Test
    fun `should not save event when event already exists`() {
        val route = newRouteEvent()
        every { routeEventRepository.findByRouteIdAndStatus(any(), any()) } returns route

        assertThrows<EntityAlreadyExistsException> { service.saveEvent(route) }

        verify(exactly = 0) { routeEventRepository.save(any()) }
    }
}
