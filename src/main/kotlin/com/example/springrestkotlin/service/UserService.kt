package com.example.springrestkotlin.service


import com.example.springrestkotlin.dto.user.UserLogin
import com.example.springrestkotlin.dto.user.UserNullDto
import com.example.springrestkotlin.dto.user.UserRegister
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface UserService {
    fun login(userLogin: UserLogin): ResponseEntity<MutableMap<String, String>>

    fun registration(userRegister: UserRegister): ResponseEntity<MutableMap<String, String>>

    fun getProfile(jwt:String) : ResponseEntity<MutableMap<String, String>>

    fun updateProfile(jwt: String, dto: UserNullDto) : ResponseEntity<HttpStatus>

}