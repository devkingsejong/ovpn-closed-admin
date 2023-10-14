package ovpn.closedadmin.server.business.account.usecase

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.common.problem.AlreadyExistsProblem

@SpringBootTest
class CreateNewVpnUserTest {

    @Autowired
    lateinit var createNewVpnUser: CreateNewVpnUser

    @Test
    @DisplayName("VpnUser를 성공적으로 생성하는지 테스트")
    fun testCreateNewVpnUser() {
        var tempUser = createNewVpnUser.createNewVpnUser("nickname", "nickname@CreateNewVpnUserTest", "12345")
        Assertions.assertEquals(
            "nickname",
            tempUser.nickname
        )

        Assertions.assertEquals(
            "nickname@CreateNewVpnUserTest",
            tempUser.email
        )

        Assertions.assertThrows(AlreadyExistsProblem::class.java) {
            createNewVpnUser.createNewVpnUser("nickname", "nickname@CreateNewVpnUserTest", "12345")
        }
    }
}
