package com.example.springrestkotlin.service

import com.example.springrestkotlin.dto.Jwt

interface UserService {
    fun login() : Jwt

    fun registration(): Jwt
}