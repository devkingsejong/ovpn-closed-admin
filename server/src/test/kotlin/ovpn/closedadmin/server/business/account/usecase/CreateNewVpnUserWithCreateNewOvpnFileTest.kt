package ovpn.closedadmin.server.business.account.usecase

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.business.account.dto.CreateVpnUserForm

@SpringBootTest
class CreateNewVpnUserWithCreateNewOvpnFileTest {

    @Autowired
    lateinit var createNewOvpnFile: CreateNewVpnUserWithCreateNewOvpnFile

    @Test
    @EnabledOnOs(OS.LINUX)
    fun testCreateNewVpnUserWithCreateNewOvpnFileSuccessful() {
        var createdOvpnFile = createNewOvpnFile.run(
            CreateVpnUserForm("nickname", "test@CreateNewVpnUserWithCreateNewOvpnFileTest", "12345")
        )

        Assertions.assertTrue(
            createdOvpnFile.contains("END OpenVPN Static key")
        )
    }
}