package ovpn.closedadmin.server.business.admin.dto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ovpn.closedadmin.server.common.problem.InvalidParameterProblem

internal class AdminLoginFormTest {

    @Test
    @DisplayName("AdminLoginForm Dto 검증이 성공적인지 테스트")
    fun testAdminLoginFormValidationIsSuccessful() {

        Assertions.assertThrows(InvalidParameterProblem::class.java) {
            AdminLoginForm("", "")
        }

        Assertions.assertThrows(InvalidParameterProblem::class.java) {
            AdminLoginForm("", "12345")
        }

        Assertions.assertThrows(InvalidParameterProblem::class.java) {
            AdminLoginForm("aaa", "")
        }

        Assertions.assertDoesNotThrow() {
            AdminLoginForm("aa", "1")
        }

        Assertions.assertDoesNotThrow() {
            AdminLoginForm("aa@aa.com", "1")
        }

        Assertions.assertDoesNotThrow() {
            AdminLoginForm("test@test.com", "likearealpassword123!")
        }

    }
}
