package ovpn.closedadmin.server.business.vpn.usecase

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SignNewUserTest {

    @Autowired
    lateinit var signNewUser: SignNewUser

    @Test
    @EnabledOnOs(OS.LINUX)
    fun shouldRunOnlyOnLinux() {
        Assertions.assertTrue(
            signNewUser.signNewUser("haha")
        )
    }
}

