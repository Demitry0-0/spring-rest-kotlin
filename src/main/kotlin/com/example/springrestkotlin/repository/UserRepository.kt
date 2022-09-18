package com.example.springrestkotlin.repository

import com.example.springrestkotlin.entity.UserEntity
import org.springframework.data.repository.CrudRepository


interface UserRepository : CrudRepository<UserEntity, Int>
{
    //fun findByIdOrNUll(id: Int) : UserEntity?
    fun findByLogin(login: String):UserEntity?
}
