package com.example.springrestkotlin.service.impl

import com.example.springrestkotlin.exception.JwtBodyException
import com.example.springrestkotlin.exception.JwtExpiredException
import com.example.springrestkotlin.exception.JwtSignatureException
import com.example.springrestkotlin.service.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.security.KeyPair
import java.time.Duration
import java.time.Instant
import java.util.*

@Service
class JwtServiceImpl() : JwtService {
    private val keyPair: KeyPair = Keys.keyPairFor(SignatureAlgorithm.RS256)
    private val minutesLeft: Long = 1
    override fun create(id: Int, login: String): String {
        return Jwts.builder().signWith(keyPair.private)
            .setHeaderParam("typ", "JWT")
            .claim("userId", id)
            .claim("login", login)
            .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(minutesLeft))))
            .compact()
    }

    override fun validate(jwt: String): Jws<Claims> {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(keyPair.private)
                .build()
                .parseClaimsJws(jwt)
        } catch (e: io.jsonwebtoken.security.SignatureException) {
            throw JwtSignatureException(e.message.toString())
        } catch (e: io.jsonwebtoken.ExpiredJwtException) {
            throw JwtExpiredException(e.message.toString())
        } catch (e: Exception) {
            throw JwtBodyException()
        }
    }
}