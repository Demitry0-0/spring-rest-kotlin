package com.example.springrestkotlin.controller

import com.example.springrestkotlin.dto.Jwt
import com.example.springrestkotlin.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/")
class UserController(
    private val userService: UserService,

    ) {
    @GetMapping
    fun testConnect() : String = "Good!"
    @PostMapping("/login")
    fun login(): Jwt = userService.login()
    @PostMapping("/registration")
    fun registration(): Jwt = userService.registration()
}