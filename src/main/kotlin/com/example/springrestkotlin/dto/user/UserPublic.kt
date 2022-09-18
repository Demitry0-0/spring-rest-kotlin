package com.example.springrestkotlin.dto.user

data class UserPublic(
    val id: Int = 0,
    val login: String,
    val first_name: String,
    val last_name: String
)