package ovpn.closedadmin.server.business.account.vo

import ovpn.closedadmin.server.business.account.enums.VpnUserStatus
import java.time.LocalDateTime
import java.util.UUID

data class VpnUser(
    var id: Long,
    var uid: UUID,
    var nickname: String,
    var status: VpnUserStatus,
    var email: String,
    var password: Password,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime) {
}
