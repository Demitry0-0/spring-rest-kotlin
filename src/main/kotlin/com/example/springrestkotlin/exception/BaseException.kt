package com.example.springrestkotlin.exception

import com.example.springrestkotlin.res.Response
import org.springframework.http.HttpStatus
import java.lang.RuntimeException

abstract class BaseException(
    val httpStatus: HttpStatus,
    val res: Response<*>,
) : RuntimeException(res.message)
