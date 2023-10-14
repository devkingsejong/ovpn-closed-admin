package ovpn.closedadmin.server.business.account.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.business.account.entity.VpnUserEntity
import ovpn.closedadmin.server.business.account.problem.LoginFailedProblem
import ovpn.closedadmin.server.business.account.problem.VpnUserNotFoundProblem
import ovpn.closedadmin.server.business.account.repository.VpnUserRepository
import ovpn.closedadmin.server.business.account.usecase.CreateNewVpnUser
import ovpn.closedadmin.server.common.util.Encrypt
import java.util.*

@SpringBootTest
class VpnUserServiceTest {

    @Autowired
    lateinit var createNewVpnUser: CreateNewVpnUser

    @Autowired
    lateinit var vpnUserService: VpnUserService

    @Autowired
    lateinit var vpnUserRepository: VpnUserRepository

    @Test
    @DisplayName("이메일과 purePassword로 유저를 정상적으로 가져올 수 있는지 테스트")
    fun testGetVpnUserByEmailAndPurePasswordSuccessful() {

        // Password "test"에 대한 유저 생성
        // password는 purepassword+salt로 구성됨.
        var tempUser = createNewVpnUser.createNewVpnUser(
            "testNickname",
            "email@VpnUserServiceImplTest",
            "12345"
        )

        Assertions.assertEquals(
            tempUser.id,
            vpnUserService.getVpnUserByEmailAndPurePassword("email@VpnUserServiceImplTest", "12345").id
        )

    }

    @Test
    @DisplayName("이메일로 유저를 정상적으로 가져올 수 있는지 테스트")
    fun testGetVpnUserByEmailSuccessful() {
        Assertions.assertThrows(VpnUserNotFoundProblem::class.java) {
            vpnUserService.getVpnUserByEmail("email@testGetVpnUserByEmailSuccessful")
        }

        var tempUser = createNewVpnUser.createNewVpnUser(
            "testNickname",
            "email@testGetVpnUserByEmailSuccessful",
            "12345"
        )

        Assertions.assertEquals(
            tempUser.email,
            vpnUserService.getVpnUserByEmail("email@testGetVpnUserByEmailSuccessful").email
        )
    }

    @Test
    @DisplayName("Uid로 유저를 정상적으로 가져올 수 있는지 테스트")
    fun testGetVpnUserByUidSuccessful() {
        var tempUUID = UUID.randomUUID()
        Assertions.assertThrows(VpnUserNotFoundProblem::class.java) {
            vpnUserService.getVpnUserByUid(tempUUID)
        }

        var tempUser = vpnUserRepository.save(
            VpnUserEntity(
                tempUUID,
                "testNickName",
                "email@testGetVpnUserByUidSuccessful",
                "SHA512\$"+ Encrypt.toSHA512("purePassword"+tempUUID.toString())
            )
        )

        Assertions.assertEquals(
            tempUser.uid,
            vpnUserService.getVpnUserByUid(tempUUID).uid
        )
    }
}
