package com.example.springrestkotlin.controller

import com.example.springrestkotlin.dto.user.UserLogin
import com.example.springrestkotlin.dto.user.UserNullDto
import com.example.springrestkotlin.dto.user.UserRegister
import com.example.springrestkotlin.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestBody


@RestController
@RequestMapping("/api")
class User(
    private val userService: UserService
    ) {

    @GetMapping
    fun testConnect(@RequestHeader("Authorization") authHeader: String){
        println(authHeader.substring(authHeader.indexOf(' ')+1))
    }


    @PostMapping("/login")
    fun login(@RequestBody req: UserLogin): ResponseEntity<MutableMap<String, String>> {
        return userService.login(req)
    }

    @PostMapping("/registration")
    fun registration(@RequestBody register: UserRegister): ResponseEntity<MutableMap<String, String>> {
        return userService.registration(register)
    }
    @GetMapping("/profile")
    fun getProfile(@RequestHeader("Authorization") authHeader: String): ResponseEntity<MutableMap<String, String>>
    = userService.getProfile(authHeader.jwt())

    @PutMapping("/profile")
    fun updateProfile(@RequestHeader("Authorization") authHeader: String, @RequestBody dto: UserNullDto)
    = userService.updateProfile(authHeader.jwt(), dto)

    fun String.jwt() = this.substring(this.indexOf(' ')+1)
}