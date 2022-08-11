package com.example.springrestkotlin.entity

import javax.persistence.*

@Entity
@Table(name="users")
class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val login: String = "",
    val password: String = "",

        ){
}