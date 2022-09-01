package com.example.springrestkotlin.service.impl

import com.example.springrestkotlin.dto.Jwt
import com.example.springrestkotlin.dto.UserDto
import com.example.springrestkotlin.entity.UserEntity
import com.example.springrestkotlin.repository.UserRepository
import com.example.springrestkotlin.service.JwtService
import com.example.springrestkotlin.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val Jwt: JwtService,
) : UserService {
    override fun login(user: UserDto?): Jwt =
        Jwt(user!!.id, Jwt.create(user.login))

    /*override fun login(): Jwt {
        return userRepository.login().map{

        }
    }
*/
    override fun registration(user: UserDto): Jwt {

        return Jwt(user.id, Jwt.create(user.login))
    }

    override fun findById(id: Int): UserDto? {
        return userRepository.findByIdOrNull(id)?.toDto()
    }

    override fun findUser(login: String): UserDto? {

        return userRepository.findByLoginOrNull().toDto()
    }

    private fun UserEntity.toDto():UserDto = UserDto(
        id = this.id,
        login = this.login,
        password = this.password,
        jwt = this.jwt,
        first_name = this.first_name,
        last_name = this.last_name
    )
}