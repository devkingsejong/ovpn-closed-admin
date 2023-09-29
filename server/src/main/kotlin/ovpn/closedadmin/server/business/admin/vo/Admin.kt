package ovpn.closedadmin.server.business.admin.vo

import ovpn.closedadmin.server.business.admin.enums.AdminStatus
import ovpn.closedadmin.server.business.account.vo.Password
import java.time.LocalDateTime
import java.util.UUID

data class Admin(
    var id: Long,
    var uid: UUID,
    var nickname: String,
    var level: Int,
    var status: AdminStatus,
    var email: String,
    var password: Password,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime) {
}
