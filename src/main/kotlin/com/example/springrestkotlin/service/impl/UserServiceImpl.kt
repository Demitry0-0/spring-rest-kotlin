package com.example.springrestkotlin.service.impl

import com.example.springrestkotlin.dto.news.NewsDto
import com.example.springrestkotlin.dto.user.UserDto
import com.example.springrestkotlin.dto.user.UserLogin
import com.example.springrestkotlin.dto.user.UserNullDto
import com.example.springrestkotlin.dto.user.UserPublic
import com.example.springrestkotlin.dto.user.UserRegister
import com.example.springrestkotlin.entity.NewsEntity
import com.example.springrestkotlin.entity.UserEntity
import com.example.springrestkotlin.repository.UserRepository
import com.example.springrestkotlin.service.JwtService
import com.example.springrestkotlin.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

import java.math.BigInteger
import java.security.MessageDigest


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val Jwt: JwtService,
) : UserService {
    override fun login(userLogin: UserLogin): ResponseEntity<MutableMap<String, String>> {
        println(userLogin)
        val user: UserDto? = findUser(userLogin.login)
        println(user)
        user ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        if (hash(userLogin.password) != user.password) return ResponseEntity(HttpStatus.BAD_REQUEST)
        return ResponseEntity(mutableMapOf("jwt" to Jwt.create(user.id, user.login)), null, HttpStatus.OK)
    }

    override fun registration(userRegister: UserRegister): ResponseEntity<MutableMap<String, String>> {
        if (findUser(userRegister.login) != null) return ResponseEntity(HttpStatus.BAD_REQUEST)
        val user: UserDto = userRepository.save<UserEntity>(userRegister.toEntity()).toDto()
        return ResponseEntity(mutableMapOf("jwt" to Jwt.create(user.id, user.login)), null, HttpStatus.OK)
    }

    override fun updateProfile( jwt: String, dto: UserNullDto): ResponseEntity<HttpStatus> {
        val data = Jwt.validate(jwt)?.body
        data ?: return ResponseEntity(HttpStatus.BAD_REQUEST)
        val user: UserEntity? = userRepository.findByIdOrNull(data["userId"] as Int)
        user ?: return ResponseEntity(HttpStatus.BAD_REQUEST)
        user.first_name = dto.first_name ?: user.first_name
        user.last_name = dto.last_name ?: user.last_name
        user.login = dto.login ?: user.login
        userRepository.save(user)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun getProfile(jwt: String): ResponseEntity<MutableMap<String, String>> {
        println(jwt)
        val data = Jwt.validate(jwt)?.body
        println("getProfile: $data")
        data ?: return ResponseEntity(HttpStatus.BAD_REQUEST)
        val user: UserDto?= userRepository.findByIdOrNull(data["userId"] as Int)?.toDto()
        user ?: return ResponseEntity(HttpStatus.BAD_REQUEST)
        return ResponseEntity(mutableMapOf("login" to user.login,
            "first_name" to user.first_name, "last_name" to user.last_name), null,  HttpStatus.OK)
    }

    fun findUser(login: String): UserDto? = userRepository.findByLogin(login)?.toDto()


    private fun UserEntity.toDto(): UserDto = UserDto(
        id = this.id,
        login = this.login,
        password = this.hash_password,
        first_name = this.first_name,
        last_name = this.last_name,
        news = this.news.map { it.toDto() }
    )
    private fun UserEntity.toUserPublic(): UserPublic = UserPublic(
        id = this.id,
        login = this.login,
        first_name = this.first_name,
        last_name = this.last_name
    )
    private fun NewsEntity.toDto():NewsDto = NewsDto(
        id = this.id,
        user = this.user.toUserPublic(),
        title = this.title,
        news_text = this.news_text,
        time_publication = this.time_publication,

    )
    private fun UserDto.toEntity():UserEntity=UserEntity(
        login = this.login,
        hash_password = hash(this.password),
        first_name = this.first_name,
        last_name = this.last_name
    )
    private fun UserRegister.toEntity(): UserEntity= UserEntity(
        login = this.login,
        hash_password = hash(this.password),
        first_name = this.first_name,
        last_name = this.last_name
    )
    private fun hash(input: String): String =
        BigInteger(
            1, MessageDigest.getInstance("MD5")
                .digest(input.toByteArray())
        ).toString(16).padStart(32, '0')

}