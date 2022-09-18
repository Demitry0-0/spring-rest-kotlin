package com.example.springrestkotlin.dto.user

import com.example.springrestkotlin.dto.news.NewsDto

data class UserDto(
    val id: Int = 0,
    val login: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val news:List<NewsDto>
)