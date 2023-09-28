package ovpn.closedadmin.server.common.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ovpn.closedadmin.server.common.dto.CommonResponse
import ovpn.closedadmin.server.common.exception.Problem

@RestControllerAdvice(basePackages = ["ovpn.closedadmin.server"])
class CommonControllerAdvice {
    @ExceptionHandler(value = [Problem::class])
    protected fun handleGlobalProblem(e: Problem): ResponseEntity<Any> {
        val response: CommonResponse<*> = CommonResponse<Any?>(e)
        return ResponseEntity<Any>(response, e.status)
    }

    @ExceptionHandler(value = [Exception::class])
    protected fun handleGlobalException(e: Exception?): ResponseEntity<Any> {
        val response: CommonResponse<*> = CommonResponse<Any?>(e)
        return ResponseEntity<Any>(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}