package ovpn.closedadmin.server.common.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.business.account.dto.JwtPayload
import ovpn.closedadmin.server.business.account.problem.InvalidTokenProblem
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
internal class JwtTest {

    @Autowired
    lateinit var jwtUtil: JwtUtil;

    @Test
    @DisplayName("올바른 입력값이 있을 때 JWT 토큰을 성공적으로 생성하는지 테스트")
    fun testGenrateJwtTotkenSuccessful() {
        Assertions.assertEquals(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjMyNDkxNjc0NjU1LCJpYXQiOjE3NzI5NzA1NSwidWlkIjoiZjVmYWRiMjUtOGVhZS00OWFjLThmMjItMDYxZWIwODM1NzgwIn0.okjODVSaTKU2frWyeVYkEovK9KwZDy66_54qMivd_gE",
            jwtUtil.generate(JwtPayload(
                    UUID.fromString("f5fadb25-8eae-49ac-8f22-061eb0835780"),
                    LocalDateTime.of(1975, 8, 15, 1, 10, 55),
                    LocalDateTime.of(2999, 8, 15, 1, 10, 55)
                )
            ))
    }

    @Test
    @DisplayName("올바른 입력값이 있을 때 JWT 토큰을 성공적으로 가져올 수 있는지 테스트")
    fun testDecodeJwtTotkenSuccessful() {
        Assertions.assertEquals(
            JwtPayload(
                UUID.fromString("f5fadb25-8eae-49ac-8f22-061eb0835780"),
                LocalDateTime.of(1975, 8, 15, 1, 10, 55),
                LocalDateTime.of(2999, 8, 15, 1, 10, 55)
            ),
            jwtUtil.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjMyNDkxNjc0NjU1LCJpYXQiOjE3NzI5NzA1NSwidWlkIjoiZjVmYWRiMjUtOGVhZS00OWFjLThmMjItMDYxZWIwODM1NzgwIn0.okjODVSaTKU2frWyeVYkEovK9KwZDy66_54qMivd_gE")
        )
    }

    @Test
    @DisplayName("조작된 입력값이 있을 때 InvalidTokenProblem을 발생시키는지 테스트")
    fun testIfInvalidTokenIsPresentedThrowsException() {
        Assertions.assertThrows(InvalidTokenProblem::class.java) {
            jwtUtil.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjMyNDkxNjc0NjU1LCJpYXQiOjE3NzI5NzA1NSwidWlkIjoiRkFLRWRiMjUtV1JPTi1HOWFjLThmMjItMDYxZWIwODM1NzgwIn0.tfSQ06cvc6SZJLw898qhbNS4EzZoUJBAUy3lzDuMlcg")
        }
    }

    @Test
    @DisplayName("만료된 입력값이 있을 때 InvalidTokenProblem을 발생시키는지 테스트")
    fun testIfExpriedTokenIsPresentedThrowsException() {
        Assertions.assertThrows(InvalidTokenProblem::class.java) {
            jwtUtil.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjEwMjkzNzM4NTUsImlhdCI6MTc3Mjk3MDU1LCJ1aWQiOiJmNWZhZGIyNS04ZWFlLTQ5YWMtOGYyMi0wNjFlYjA4MzU3ODAifQ.zHQccjnLlZKx3dGFgqwksjLwH5qu0Y7gD-6thY40SEc")
        }
    }

}
