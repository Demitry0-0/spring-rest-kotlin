package com.example.springrestkotlin.service.impl

import com.example.springrestkotlin.dto.Jwt
import com.example.springrestkotlin.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override fun login(): Jwt {
        return Jwt(1, "xfsfds")
    }

    override fun registration(): Jwt {
        return Jwt(1, "xfsfds")
    }
}