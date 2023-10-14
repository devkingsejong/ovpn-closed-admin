package ovpn.closedadmin.server.business.account.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.business.account.enums.VpnUserStatus
import ovpn.closedadmin.server.business.account.repository.VpnUserRepository
import ovpn.closedadmin.server.business.account.vo.Password
import ovpn.closedadmin.server.business.account.vo.VpnUser
import java.util.*

@SpringBootTest
class VpnUserEntityTest {

    @Autowired
    lateinit var vpnUserRepository: VpnUserRepository

    @Test
    @DisplayName("VpnUserEntity toVO()가 정상적으로 동작하는지 테스트")
    fun testVpnUserEntityToVOSuccessful() {
        val tempUser = VpnUserEntity(UUID.randomUUID(), "test", "a@a.com", "test\$test");
        vpnUserRepository.save(tempUser)

        Assertions.assertEquals(
            VpnUser(
                tempUser.id!!,
                tempUser.uid,
                tempUser.nickname,
                VpnUserStatus.ACTIVE,
                tempUser.email,
                Password(tempUser.password),
                tempUser.createdAt,
                tempUser.updatedAt
            ),
            tempUser.toVO()
        )
    }
}
