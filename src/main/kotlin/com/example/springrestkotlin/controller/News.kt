package com.example.springrestkotlin.controller

import com.example.springrestkotlin.dto.news.NewsDto
import com.example.springrestkotlin.res.Response
import com.example.springrestkotlin.service.NewsService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class News (
    private val newsService: NewsService
) {
    @GetMapping("/feed")
    fun getAllNews(
        @RequestParam("page", defaultValue = "0") pageIndex: Int,
        @RequestParam("size", defaultValue = "10") pageSize: Int
    ): Response<*> = newsService.getAll(pageIndex, pageSize)

    @PostMapping("/feed")
    fun addNews(@RequestHeader("Authorization") authHeader: String, @RequestBody newsDto: NewsDto) {
        println("$authHeader\n$newsDto")
        newsService.add(authHeader.toJwt(), newsDto)
    }

    @PostMapping("/like/{newsId}")
    fun update(@RequestHeader("Authorization") authHeader: String, @PathVariable("newsId") newsId: Int) {
        println("$authHeader $newsId")
        newsService.like(authHeader.toJwt(), newsId)
    }

    fun String.toJwt() = this.substring(this.indexOf(' ')+1)
}