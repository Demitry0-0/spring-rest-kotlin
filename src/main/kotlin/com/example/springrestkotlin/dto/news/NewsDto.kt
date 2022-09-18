package com.example.springrestkotlin.dto.news

import com.example.springrestkotlin.dto.user.UserPublic
import java.time.Instant
import java.util.*

data class NewsDto(
    val id: Int = 0,
    val user: UserPublic ?=null,
    val title: String,
    val news_text: String,
    val images_url: List<String> = emptyList(),
    val time_publication: Date = Date.from(Instant.now()),
)