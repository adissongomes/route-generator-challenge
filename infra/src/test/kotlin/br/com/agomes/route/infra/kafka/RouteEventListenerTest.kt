package br.com.agomes.route.infra.kafka

import br.com.agomes.route.exception.EntityAlreadyExistsException
import br.com.agomes.route.dto.RouteEventDTO
import br.com.agomes.route.usecase.SaveRouteEventUseCase
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Instant
import java.util.UUID

@ExtendWith(MockKExtension::class)
class RouteEventListenerTest {

    @MockK
    private lateinit var saveRouteEventUseCase: SaveRouteEventUseCase

    @InjectMockKs
    private lateinit var routeEventListener: RouteEventListener

    @Test
    fun `should handle route event`() {
        justRun { saveRouteEventUseCase.save(any()) }

        routeEventListener.handle(createRouteEventDTO())

        verify { saveRouteEventUseCase.save(any()) }
    }

    @Test
    fun `should ignore error when route already exists`() {
        justRun { saveRouteEventUseCase.save(any()) } andThenThrows EntityAlreadyExistsException("")

        assertDoesNotThrow { routeEventListener.handle(createRouteEventDTO()) }
    }

    private fun createRouteEventDTO() = RouteEventDTO(
        id = UUID.randomUUID(),
        originId = UUID.randomUUID(),
        destinationId = UUID.randomUUID(),
        courierId = UUID.randomUUID(),
        status = "CREATED",
        eventTime = Instant.now()
    )
}
