package com.example.springrestkotlin.exception

import com.example.springrestkotlin.res.Response
import org.springframework.http.HttpStatus

class Exceptions {
}

class JwtBodyException() : BaseException(
    HttpStatus.BAD_REQUEST,
    Response<Any>(
        //title = "jwt.body",
        message = "Errors jwt body. Bad token body",
        code = HttpStatus.BAD_REQUEST
    )
)
class JwtExpiredException(val mes: String) : BaseException(
    HttpStatus.BAD_REQUEST,
    Response<Any>(
        //title = "jwt.expired",
        message = mes,
        code = HttpStatus.BAD_REQUEST
    )
)
class JwtSignatureException(val mes: String) : BaseException(
    HttpStatus.BAD_REQUEST,
    Response<Any>(
        //title = "jwt.expired",
        message = mes,
        code = HttpStatus.BAD_REQUEST
    )
)
class NewsNotFoundException(newsId: Int) : BaseException(
    HttpStatus.NOT_FOUND,
    Response<Any>(
        //title = "news.not.found",
        message = "News not found with id=$newsId",
        code = HttpStatus.NOT_FOUND
    )
)
class UserAlreadyRegisteredException(login: String) : BaseException(
    HttpStatus.BAD_REQUEST,
    Response<Any>(
        //title = "user.already.registered",
        message = "Such user with such login=$login is already registered",
        code = HttpStatus.BAD_REQUEST
    )
)
class UserNotFoundException(login: String) : BaseException(
    HttpStatus.NOT_FOUND,
    Response<Any>(
        //title = "user.not.found",
        message = "User not found with email=$login",
        code = HttpStatus.NOT_FOUND
    )
)
class UserWrongPasswordException() : BaseException(
    HttpStatus.BAD_REQUEST,
    Response<Any>(
        //title = "user.wrong.password",
        message = "User wrong password",
        code = HttpStatus.BAD_REQUEST
    )
)