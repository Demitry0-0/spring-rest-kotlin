package com.example.springrestkotlin.repository

import com.example.springrestkotlin.entity.ImagesNewsEntity
import org.springframework.data.repository.CrudRepository


interface ImagesNewsRepository : CrudRepository<ImagesNewsEntity, Int> {

}
