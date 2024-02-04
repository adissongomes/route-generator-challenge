package br.com.agomes.route.api

import br.com.agomes.route.exception.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AppControllerAdvice {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException) =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDetail(e.message))
}

data class ErrorDetail(val detail: String?)
