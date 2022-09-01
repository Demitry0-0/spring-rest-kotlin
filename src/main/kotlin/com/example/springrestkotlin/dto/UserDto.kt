package com.example.springrestkotlin.dto

data class UserDto(
    val id: Int = 0,
    val login: String = "",
    val password: String = "",
    val jwt: String = "",
    val first_name: String = "",
    val last_name: String = "",
)
