package ovpn.closedadmin.server.business.account.dto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ovpn.closedadmin.server.common.problem.InvalidParameterProblem


class VpnUserLoginFormTest {
    @Test
    @DisplayName("VpnUserLoginForm Dto 검증이 성공적인지 테스트")
    fun testVpnUserLoginFormValidationIsSuccessful() {

        Assertions.assertThrows(InvalidParameterProblem::class.java) {
            VpnUserLoginForm("", "")
        }

        Assertions.assertThrows(InvalidParameterProblem::class.java) {
            VpnUserLoginForm("", "12345")
        }

        Assertions.assertThrows(InvalidParameterProblem::class.java) {
            VpnUserLoginForm("aaa", "")
        }

        Assertions.assertDoesNotThrow() {
            VpnUserLoginForm("aa", "1")
        }

        Assertions.assertDoesNotThrow() {
            VpnUserLoginForm("aa@aa.com", "1")
        }

        Assertions.assertDoesNotThrow() {
            VpnUserLoginForm("test@test.com", "likearealpassword123!")
        }

    }
}