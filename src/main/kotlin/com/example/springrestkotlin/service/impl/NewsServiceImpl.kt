package com.example.springrestkotlin.service.impl

import com.example.springrestkotlin.dto.news.NewsDto
import com.example.springrestkotlin.dto.user.UserPublic
import com.example.springrestkotlin.entity.ImagesNewsEntity
import com.example.springrestkotlin.entity.LikesEntity
import com.example.springrestkotlin.entity.NewsEntity
import com.example.springrestkotlin.entity.UserEntity
import com.example.springrestkotlin.exception.JwtBodyException
import com.example.springrestkotlin.exception.NewsNotFoundException
import com.example.springrestkotlin.repository.ImagesNewsRepository
import com.example.springrestkotlin.repository.LikesRepository
import com.example.springrestkotlin.repository.NewsRepository
import com.example.springrestkotlin.repository.UserRepository
import com.example.springrestkotlin.res.Response
import com.example.springrestkotlin.service.JwtService
import com.example.springrestkotlin.service.NewsService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Date


@Service
class NewsServiceImpl(
    private val newsRepository: NewsRepository,
    private val userRepository: UserRepository,
    private val imagesNewsRepository: ImagesNewsRepository,
    private val likesRepository: LikesRepository,
    private val Jwt: JwtService,
) : NewsService {
    override fun getAll(pageIndex: Int, pageSize: Int): Response<List<NewsDto>> {
        return Response(
            payload =
            newsRepository.findByOrderByIdDesc(
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    Sort.by("id").descending()
                )
            )
                .map { it.toDto() },
            code = HttpStatus.OK
        )
    }

    override fun add(jwt: String, newsDto: NewsDto): Response<Any> {
        val data = Jwt.validate(jwt)?.body
        data ?: throw JwtBodyException()

        val user: UserEntity? = userRepository.findByIdOrNull(data["userId"] as Int)
        user ?: throw JwtBodyException()

        val newsId: Int = newsRepository.save(newsDto.toEntity(user)).id
        imagesNewsRepository.saveAll(newsDto.images_url.map {
            ImagesNewsEntity(
                news_id = newsId,
                image_url = it
            )
        })
        return Response(code = HttpStatus.OK)
    }

    override fun like(jwt: String, newsId: Int): Response<Any> {
        val data = Jwt.validate(jwt)?.body
        data ?: throw JwtBodyException()

        val user: UserEntity? = userRepository.findByIdOrNull(data["userId"] as Int)
        user ?: throw JwtBodyException()

        val news: NewsEntity? = newsRepository.findByIdOrNull(newsId)
        news ?: throw NewsNotFoundException(newsId)

        val likesEntity: LikesEntity? = likesRepository.findByNews_IdAndUser_Id(news.id, user.id)
        if (likesEntity == null)
            likesRepository.save(
                LikesEntity(
                    news = news,
                    user = user
                )
            )
        else
            likesRepository.delete(likesEntity)
        return Response(code = HttpStatus.OK)
    }


    private fun NewsDto.toEntity(user: UserEntity) = NewsEntity(
        user = user,
        title = this.title,
        news_text = this.news_text,
        time_publication = Date.from(Instant.now())
    )

    private fun NewsEntity.toDto(): NewsDto =
        NewsDto(
            id = this.id,
            user = this.user.toUserPublic(),
            title = this.title,
            news_text = this.news_text,
            time_publication = this.time_publication,
            images_url = this.images_url.map { it.image_url }
        )

    private fun UserEntity.toUserPublic(): UserPublic = UserPublic(
        id = this.id,
        login = this.login,
        first_name = this.first_name,
        last_name = this.last_name
    )

}