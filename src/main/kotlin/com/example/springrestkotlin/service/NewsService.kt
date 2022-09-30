package com.example.springrestkotlin.service

import com.example.springrestkotlin.dto.news.NewsDto
import com.example.springrestkotlin.res.Response

interface NewsService {
    fun getAll(pageIndex:Int, pageSize: Int) : Response<List<NewsDto>>
    fun add(jwt: String, newsDto: NewsDto) : Response<Any>
    fun like(jwt: String, newsId:Int) : Response<Any>
}