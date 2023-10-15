package ovpn.closedadmin.server.business.account.service

import ovpn.closedadmin.server.business.account.vo.VpnUser
import java.util.UUID

interface VpnUserService {
    fun getVpnUserByEmailAndPurePassword(email: String, purePassword: String): VpnUser
    fun getVpnUserByEmail(email: String): VpnUser
    fun getVpnUserByUid(uid: UUID): VpnUser
    fun getVpnUserList(page: Int): List<VpnUser>
}
