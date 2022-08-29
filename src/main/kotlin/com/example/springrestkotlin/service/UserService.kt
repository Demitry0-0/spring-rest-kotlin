package com.example.springrestkotlin.service

import com.example.springrestkotlin.dto.Jwt
import com.example.springrestkotlin.dto.UserDto

interface UserService {
    fun login(user:UserDto ?) : Jwt

    fun registration(user:UserDto): Jwt
}