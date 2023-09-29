package ovpn.closedadmin.server.business.admin.service

import ovpn.closedadmin.server.business.admin.vo.Admin

interface AdminAccountService {
    fun getAdminByEmailAndPurePassword(email: String, purePassword: String): Admin
}
