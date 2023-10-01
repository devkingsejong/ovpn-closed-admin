package ovpn.closedadmin.server.common.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ovpn.closedadmin.server.business.account.dto.JwtPayload
import ovpn.closedadmin.server.business.account.problem.InvalidTokenProblem
import java.time.Instant
import java.time.ZoneOffset
import java.util.*
import javax.crypto.SecretKey
import kotlin.reflect.full.memberProperties

@Component
class JwtUtil {

    @Value("\${jwt.key}")
    lateinit var jwtKey: String

    fun generate(payload: JwtPayload): String {
        var secretKey: SecretKey = Keys.hmacShaKeyFor(this.jwtKey.toByteArray(Charsets.UTF_8))
        var now: Instant = Date().toInstant();

        var jws = Jwts.builder()
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .setHeaderParam("typ", "JWT")

        payload::class.memberProperties.forEach { property ->
            jws.claim(property.name, property.call(payload))
        }

        return jws.setIssuedAt(Date.from(payload.iat.toInstant(ZoneOffset.UTC)))
            .setExpiration(Date.from(payload.exp.toInstant(ZoneOffset.UTC)))
            .compact()
    }

    fun decode(jwt: String): JwtPayload {
        try {
            val secretKey: SecretKey = Keys.hmacShaKeyFor(this.jwtKey.toByteArray(Charsets.UTF_8))

            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .body
            val objectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
            return objectMapper.convertValue(claims, JwtPayload::class.java)
        } catch (e: Exception) {
            println("Error occurred while decoding token: ${e.message}")
            throw InvalidTokenProblem()
        }
    }
}
