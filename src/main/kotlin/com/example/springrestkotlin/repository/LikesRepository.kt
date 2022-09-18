package com.example.springrestkotlin.repository

import com.example.springrestkotlin.entity.LikesEntity
import com.example.springrestkotlin.entity.NewsEntity
import com.example.springrestkotlin.entity.UserEntity
import org.springframework.data.repository.CrudRepository


interface LikesRepository : CrudRepository<LikesEntity, Int> {
    fun findByNews_IdAndUser_Id(newsId:Int, userId:Int):LikesEntity?
}
