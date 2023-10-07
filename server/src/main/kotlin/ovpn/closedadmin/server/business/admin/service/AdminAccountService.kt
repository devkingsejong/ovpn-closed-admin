package ovpn.closedadmin.server.business.admin.service

import ovpn.closedadmin.server.business.admin.vo.Admin
import java.util.UUID

interface AdminAccountService {
    fun getAdminByEmailAndPurePassword(email: String, purePassword: String): Admin
    fun getAdminByUid(uid: UUID): Admin
}
