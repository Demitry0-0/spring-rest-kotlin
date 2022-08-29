package com.example.springrestkotlin.service.impl

import com.example.springrestkotlin.dto.Jwt
import com.example.springrestkotlin.dto.UserDto
import com.example.springrestkotlin.repository.UserRepository
import com.example.springrestkotlin.service.JwtService
import com.example.springrestkotlin.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val Jwt: JwtService,
) : UserService {
    override fun login(user:UserDto ?): Jwt =
        Jwt(user!!.id, Jwt.create(user.login))

    /*override fun login(): Jwt {
        return userRepository.login().map{

        }
    }
*/
    override fun registration(user:UserDto): Jwt {
        userRepository.findByOrderByName().map{}
        return Jwt(user.id, Jwt.create(user.login))
    }
}