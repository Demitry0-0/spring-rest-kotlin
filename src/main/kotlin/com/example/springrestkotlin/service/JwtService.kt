package com.example.springrestkotlin.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws

interface JwtService {
    fun create(login: String): String
    fun validate(jwt: String): Jws<Claims>
}