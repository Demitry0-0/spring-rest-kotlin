package com.example.springrestkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.math.BigInteger
import java.security.Key;
import java.security.MessageDigest
import java.time.Instant


@SpringBootApplication
class SpringRestKotlinApplication
//nullnullheader={alg=RS256},body={sub=email@example.com, iss=identity, exp=1662214436, iat=1662213536},signature=R1HY9bHaaW0FPf4sLVoGnu72mTL88PMQ8cCm_5G4dHgTy0QTHTIJRjuSr3M3e0Aj-kZvJ9IvlpdhxLE1045gHCscflljGi56DyM3BZqOHp0-HpCc97ytY97QCNB6VNYXg1-_wJAeufRkDDZQCDdsBHVFJy8ntWWJVtNI5qfB1eiYUKCppk0XC8O3Q0HiT-RckisWWeQPyu8Hr62EC8pVkG15ZEK5rITOqbGUUg38EAeZ7R7Vi4E_cToMELoN-DRQGh24Pp8f9Xac8wGPXDJvXm_4Lc8nKxskTWS5pskt8yKbLToAjKmDY1h7UtoddpkEYZwYDMPzGLMOzpr2JU6qKw


fun example1(){
	// We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.
	val signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)

// Generate JWT String
	val exampleJwt = Jwts.builder().setSubject("Joe").signWith(signingKey).compact()
	System.out.println("exampleJwt: $exampleJwt")
// => exampleJwt: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb2UifQ.EzFGmEj9KG9zrNsMBc0Y2YoUVuQJHL45uLrWFfj5CTBasArXAI-IEf_A0jYTKBZNhwLz3-NWEekPf8tll4yJVQ

// Verify JWT String
	val claims = Jwts.parser().
	setSigningKey(signingKey).
	parseClaimsJws(exampleJwt)
	System.out.println("Decode result: $claims")
// => Decode result: {sub=Joe}

}
fun example2(){
	//var keyStr = "DummyKeyStringDummyKeyStringDummyKeyStringDummyKeyStringDummyKeyStringDummyKeyString"
	//val key = Keys.hmacShaKeyFor(keyStr.toByteArray())
	val signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
	val jwt = Jwts.builder().claim("userId", 1).signWith(signingKey).compact()
// => example2Jwt: eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjF9.VApKjgGHRqTB-LZaYw33kf0ovTjgpCb153HRxQCfiYBQGfLwu0fSsRQHv4dAeCm33YNxAgLJ5doi6G2bCIBS5A

	System.out.println("example2Jwt: $jwt")
	val claims = Jwts.parser()
		.setSigningKey(signingKey)
		.parseClaimsJws(jwt)
	System.out.println("Decode result: $claims userId: ${claims.body["userId"]}")
// => Decode result: header={alg=HS512},body={userId=1},signature=VApKjgGHRqTB-LZaYw33kf0ovTjgpCb153HRxQCfiYBQGfLwu0fSsRQHv4dAeCm33YNxAgLJ5doi6G2bCIBS5A userId: 1
}

fun main(args: Array<String>) {
//println(Date.from(Instant.now()))
	runApplication<SpringRestKotlinApplication>(*args)
	//example1()
	//example2()
	/*
	* user_id
	* login
	* exp
	*/

}
