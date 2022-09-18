package com.example.springrestkotlin.repository

import com.example.springrestkotlin.entity.NewsEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.domain.Pageable


interface NewsRepository : CrudRepository<NewsEntity, Int>
{
    fun findByOrderByIdDesc(pageable: Pageable) : List<NewsEntity>
}
