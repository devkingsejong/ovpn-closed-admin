package ovpn.closedadmin.server.business.password.vo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ovpn.closedadmin.server.business.account.exception.InvaliedPasswordVOException
import ovpn.closedadmin.server.business.account.vo.Password

internal class PasswordTest {
    @Test
    @DisplayName("Password VO를 정상적으로 생성하는지 테스트")
    fun testBuildPasswordVoSuccessful() {
        val password1 = Password("asdf$12345");
        Assertions.assertEquals(
            password1.toString(),
            "asdf$12345"
        )

        Assertions.assertEquals(
            password1.passwordEncrypter,
            "asdf"
        )

        Assertions.assertEquals(
            password1.hashedPassword,
            "12345"
        )
    }

    @Test
    @DisplayName("PasswordVO 생성시 비정상적인 값이 들어오면 오류가 발생하는지 테스트")
    fun testIfParameterIsInvalidMethodThrowsException() {
        Assertions.assertThrows(InvaliedPasswordVOException::class.java) {
            Password("asdf12345")
        }

        Assertions.assertThrows(InvaliedPasswordVOException::class.java) {
            Password("asdf$")
        }

        Assertions.assertThrows(InvaliedPasswordVOException::class.java) {
            Password("$12345")
        }

        Assertions.assertThrows(InvaliedPasswordVOException::class.java) {
            Password("")
        }

        Assertions.assertThrows(InvaliedPasswordVOException::class.java) {
            Password(" ")
        }
    }
}
