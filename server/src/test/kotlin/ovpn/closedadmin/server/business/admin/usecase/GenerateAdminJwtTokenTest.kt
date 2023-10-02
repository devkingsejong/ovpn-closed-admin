package ovpn.closedadmin.server.business.admin.usecase

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.business.account.problem.LoginFailedProblem
import ovpn.closedadmin.server.business.admin.dto.AdminLoginForm
import ovpn.closedadmin.server.common.util.JwtUtil

@SpringBootTest
internal class GenerateAdminJwtTokenTest @Autowired constructor(private var generateAdminJwtToken: GenerateAdminJwtToken, private var jwtUtil: JwtUtil) {

    @Test
    @DisplayName("성공적으로 generateJwtToken()를 수행하는지 테스트")
    fun testGenerateJwtTokenSuccessful() {
        Assertions.assertThrows(LoginFailedProblem::class.java) {
            generateAdminJwtToken.generateJwtToken(
                AdminLoginForm("noemail@noemail.com", "nopassword")
            )
        }

        Assertions.assertThrows(LoginFailedProblem::class.java) {
            generateAdminJwtToken.generateJwtToken(
                AdminLoginForm("admin@admin", "wrongPassword")
            )
        }

        Assertions.assertDoesNotThrow() {
            jwtUtil.decode(
                generateAdminJwtToken.generateJwtToken(
                    AdminLoginForm("admin@admin", "test")
                )
            )
        }

    }
}
