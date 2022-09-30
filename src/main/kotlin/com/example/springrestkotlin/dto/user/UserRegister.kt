package com.example.springrestkotlin.dto.user

data class UserRegister(
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
)