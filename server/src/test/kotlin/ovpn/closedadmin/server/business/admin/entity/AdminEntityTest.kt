package ovpn.closedadmin.server.business.admin.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.business.admin.enums.AdminStatus
import ovpn.closedadmin.server.business.admin.repository.AdminRepository
import ovpn.closedadmin.server.business.admin.vo.Admin
import ovpn.closedadmin.server.business.password.vo.Password
import java.util.*

@SpringBootTest
internal class AdminEntityTest {

    @Autowired
    lateinit var adminRepository: AdminRepository

    @Test
    @DisplayName("AdminEntity toVO()가 정상적으로 동작하는지 테스트")
    fun testAdminEntityToVOSuccessful() {
        val tempUser = AdminEntity(UUID.randomUUID(), "test", 5, "a@a.com", "test\$test");
        adminRepository.save(tempUser)

        Assertions.assertEquals(
            Admin(
                tempUser.id!!,
                tempUser.uid,
                tempUser.nickname,
                tempUser.level,
                AdminStatus.ACTIVE,
                tempUser.email,
                Password(tempUser.password),
                tempUser.createdAt,
                tempUser.updatedAt
            ),
            tempUser.toVO()
        )
    }
}
