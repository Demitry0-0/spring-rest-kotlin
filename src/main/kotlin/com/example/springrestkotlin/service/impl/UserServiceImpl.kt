package com.example.springrestkotlin.service.impl

import com.example.springrestkotlin.dto.Jwt
import com.example.springrestkotlin.dto.UserDto
import com.example.springrestkotlin.repository.UserRepository
import com.example.springrestkotlin.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun login(): Jwt =
        Jwt(1, "xfsfds")

    /*override fun login(): Jwt {
        return userRepository.login().map{

        }
    }
*/
    override fun registration(): Jwt {
        return Jwt(1, "xfsfds")
    }
}