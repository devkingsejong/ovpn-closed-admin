package ovpn.closedadmin.server.business.admin.enums

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ovpn.closedadmin.server.business.admin.entity.AdminEntity

internal class AdminStatusTest {

    @Test
    @DisplayName("String으로부터 AdminStatus enum을 성공적으로 생성하는지 테스트")
    fun testGetAdminStatusByStatusCodeSuccessful(){
        Assertions.assertEquals(
            AdminStatus.ACTIVE,
            AdminStatus.getAdminStatusByStatusCode(AdminEntity._AdminStatus.ACTIVE.toString())
        )

        Assertions.assertEquals(
            AdminStatus.BLOCKED,
            AdminStatus.getAdminStatusByStatusCode(AdminEntity._AdminStatus.BLOCKED.toString())
        )

        Assertions.assertEquals(
            AdminStatus.DELETED,
            AdminStatus.getAdminStatusByStatusCode(AdminEntity._AdminStatus.DELETED.toString())
        )
    }
}
