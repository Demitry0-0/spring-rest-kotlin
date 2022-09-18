package com.example.springrestkotlin.service.impl

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
    private val minutesLeft :Long = 15
    override fun create(id:Int, login: String): String {
        return Jwts.builder().signWith(keyPair.private)
            .setHeaderParam("typ", "JWT")
            .claim("userId", id)
            .claim("login", login)
            .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(minutesLeft))))
            .compact()
/*
        Jwts.builder()
            .signWith(keyPair.private, SignatureAlgorithm.RS256)
            .setSubject(login)
            .setIssuer("identity")
            .setIssuedAt(Date.from(Instant.now()))
            .compact()
*/
    }

    override fun validate(jwt: String): Jws<Claims>? {
        return try{
            Jwts.parserBuilder()
            .setSigningKey(keyPair.private)
            .build()
            .parseClaimsJws(jwt)
        }
        catch (e: io.jsonwebtoken.security.SignatureException){
            println("ERROR: ${e.message}")
            null
        }
    }
}