package com.example.springrestkotlin.service


import com.example.springrestkotlin.dto.user.UserLogin
import com.example.springrestkotlin.dto.user.UserNullDto
import com.example.springrestkotlin.dto.user.UserRegister
import com.example.springrestkotlin.res.Response

interface UserService {
    fun login(userLogin: UserLogin): Response<MutableMap<String, String>>

    fun registration(userRegister: UserRegister): Response<MutableMap<String, String>>

    fun getProfile(jwt: String): Response<Any>

    fun updateProfile(jwt: String, dto: UserNullDto): Response<Any>

}