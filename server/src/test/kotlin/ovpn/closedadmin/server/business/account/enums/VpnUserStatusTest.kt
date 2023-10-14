package ovpn.closedadmin.server.business.account.enums

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ovpn.closedadmin.server.business.account.entity.VpnUserEntity
import ovpn.closedadmin.server.business.admin.entity.AdminEntity
import ovpn.closedadmin.server.business.admin.enums.AdminStatus

class VpnUserStatusTest {
    @Test
    @DisplayName("String으로부터 VpnUserStatus enum을 성공적으로 생성하는지 테스트")
    fun testGetVpnUSerStatusByStatusCodeSuccessful(){
        Assertions.assertEquals(
            VpnUserStatus.ACTIVE,
            VpnUserStatus.getVpnUserStatusByStatusCode(VpnUserEntity._VpnUserStatus.ACTIVE.toString())
        )

        Assertions.assertEquals(
            VpnUserStatus.BLOCKED,
            VpnUserStatus.getVpnUserStatusByStatusCode(VpnUserEntity._VpnUserStatus.BLOCKED.toString())
        )

        Assertions.assertEquals(
            VpnUserStatus.DELETED,
            VpnUserStatus.getVpnUserStatusByStatusCode(VpnUserEntity._VpnUserStatus.DELETED.toString())
        )

    }
}
