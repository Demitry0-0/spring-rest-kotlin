package com.example.springrestkotlin.exception

import com.example.springrestkotlin.res.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(exception: BaseException): ResponseEntity<Response<*>> =
        ResponseEntity(exception.res, exception.httpStatus)

}