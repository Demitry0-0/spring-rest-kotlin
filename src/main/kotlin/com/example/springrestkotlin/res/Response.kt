package com.example.springrestkotlin.res

import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

data class Response<T>(
    val payload: T? = null,
    val message: String = "",
    val code: HttpStatus,
    val status: Int = code.value()
    /*
    val payload, -> json
    val message="",
    val title="",
    val code -> status code
*/
)
