package com.example.springrestkotlin.service

import com.example.springrestkotlin.dto.news.NewsDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface NewsService {
    fun getAll(pageIndex:Int, pageSize: Int) : List<NewsDto>
    fun add(jwt: String, newsDto: NewsDto) : ResponseEntity<HttpStatus>
    fun like(jwt: String, newsId:Int) : ResponseEntity<HttpStatus>
}