package ovpn.closedadmin.server.business.account.usecase

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.business.account.dto.VpnUserLoginForm
import ovpn.closedadmin.server.business.account.problem.LoginFailedProblem
import ovpn.closedadmin.server.common.constant.ReferenceValues
import ovpn.closedadmin.server.common.util.JwtUtil

@SpringBootTest
class GenerateVpnUserJwtTokenTest {

    @Autowired
    lateinit var generateVpnUserJwtToken: GenerateVpnUserJwtToken

    @Autowired
    lateinit var jwtUtil: JwtUtil

    @Test
    @DisplayName("성공적으로 generateJwtToken()를 수행하는지 테스트")
    fun testGenerateJwtTokenSuccessful() {
        Assertions.assertThrows(LoginFailedProblem::class.java) {
            generateVpnUserJwtToken.generateJwtToken(
                VpnUserLoginForm("noemail@vpnuser.com", "password")
            )
        }

        Assertions.assertThrows(LoginFailedProblem::class.java) {
            generateVpnUserJwtToken.generateJwtToken(
                VpnUserLoginForm(
                    ReferenceValues.DEFAULT_VPN_USER_EMAIL, "wrongPassword")
            )
        }

        Assertions.assertDoesNotThrow() {
            jwtUtil.decode(
                generateVpnUserJwtToken.generateJwtToken(
                    VpnUserLoginForm(ReferenceValues.DEFAULT_VPN_USER_EMAIL, ReferenceValues.DEFAULT_VPN_USER_PASSWORD)
                )
            )
        }
    }
}
