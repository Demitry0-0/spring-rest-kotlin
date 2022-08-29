package com.example.springrestkotlin.controller

import com.example.springrestkotlin.dto.Jwt
import com.example.springrestkotlin.dto.UserDto
import com.example.springrestkotlin.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


data class UserCredentials(val email: String, val password: String)

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService,

    ) {
    private val users: MutableMap<String, UserDto> = mutableMapOf()
    private var cnt: Int = 0;

    @GetMapping
    fun testConnect(): String = "Good!"


    data class UserRequest(val email: String, val password: String)

    @PostMapping("/login")
    fun login(@RequestBody req: UserRequest): ResponseEntity<Jwt> {
        val user: UserDto? = users[req.email]
        user ?: return ResponseEntity(HttpStatus.BAD_REQUEST)
        val jwt: Jwt = userService.login(user)
        return ResponseEntity<Jwt>(jwt,null, HttpStatus.OK)
    }

    @PostMapping("/registration")
    fun registration(@RequestBody req: UserRequest): ResponseEntity<Jwt> {

        if (users[req.email] !== null)  return ResponseEntity(HttpStatus.BAD_REQUEST)
        val user: UserDto = UserDto(++cnt, req.email, req.password)
        users[req.email] = user
        return ResponseEntity<Jwt>(userService.registration(user),null, HttpStatus.OK)
    }
}