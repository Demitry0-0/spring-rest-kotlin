package com.example.springrestkotlin.entity

import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val login: String = "",
    val password: String = "",
    val jwt: String = "",
    val first_name: String = "",
    val last_name: String = "",
)
