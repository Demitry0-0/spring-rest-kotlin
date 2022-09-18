package com.example.springrestkotlin.dto.user

data class UserNullDto(
    val login: String?=null,
    val first_name: String?=null,
    val last_name: String?=null,
)