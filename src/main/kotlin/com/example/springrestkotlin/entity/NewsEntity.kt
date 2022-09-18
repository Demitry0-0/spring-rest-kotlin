package com.example.springrestkotlin.entity


import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name="news")
class NewsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user : UserEntity,
    val title: String,
    val news_text: String,
    @OneToMany(mappedBy = "news_id")
    val images_url : List<ImagesNewsEntity> = emptyList(),
    val time_publication: Date = Date.from(Instant.now())
)