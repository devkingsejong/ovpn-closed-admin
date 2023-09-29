package ovpn.closedadmin.server.business.admin.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.business.admin.entity.AdminEntity
import ovpn.closedadmin.server.business.admin.repository.AdminRepository
import ovpn.closedadmin.server.business.password.exception.InvaliedPasswordVOException
import ovpn.closedadmin.server.business.password.problem.LoginFailedProblem
import ovpn.closedadmin.server.business.password.vo.Password
import java.util.*

@SpringBootTest
internal class AdminAccountServiceImplTest{

    @Autowired
    lateinit var adminRepository: AdminRepository

    @Autowired
    lateinit var adminAccountService: AdminAccountService

    @Test
    @DisplayName("이메일과 purePassword로 유저를 정상적으로 가져올 수 있는지 테스트")
    fun testGetAdminByEmailAndPurePasswordSuccessful() {

        // Password "test"에 대한 유저 생성
        // password는 purepassword+salt로 구성됨.
        val tempUser = AdminEntity(
            UUID.fromString("f5fadb25-8eae-49ac-8f22-061eb0835780"),
            "test2",
            5,
            "a2@a2.com",
            "SHA512\$b7eacc5ff9dc139494a22600958deb8dc3108e53d06607de211ae599f56e2c500ce031acf454ff4a6fe5cd0a5cb20b31eaa763eb9ac7ccd33b8cf034ff616a79"
        );
        adminRepository.save(tempUser)

        Assertions.assertEquals(
            tempUser.id,
            adminAccountService.getAdminByEmailAndPurePassword("a2@a2.com", "test").id
        )

        Assertions.assertThrows(LoginFailedProblem::class.java) {
            adminAccountService.getAdminByEmailAndPurePassword("a2@a2.com", "wrongPW")
        }

        Assertions.assertThrows(LoginFailedProblem::class.java) {
            adminAccountService.getAdminByEmailAndPurePassword("wrongEmail@a2.com", "test")
        }

        Assertions.assertThrows(LoginFailedProblem::class.java) {
            adminAccountService.getAdminByEmailAndPurePassword("wrongEmail@a2.com", "wrongPW")
        }
    }
}
