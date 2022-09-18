package com.example.springrestkotlin.entity

import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var login: String = "",
    var hash_password: String = "",
    var first_name: String = "",
    var last_name: String = "",
    @OneToMany(mappedBy = "user")
    val news : List<NewsEntity> = emptyList()
)
