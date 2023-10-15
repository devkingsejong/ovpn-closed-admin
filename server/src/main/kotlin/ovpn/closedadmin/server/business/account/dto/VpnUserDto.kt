package ovpn.closedadmin.server.business.account.dto

import ovpn.closedadmin.server.business.account.enums.VpnUserStatus
import ovpn.closedadmin.server.business.account.vo.VpnUser
import java.time.LocalDateTime
import java.util.UUID

data class VpnUserDto(
    var uid: UUID,
    var nickname: String,
    var status: VpnUserStatus,
    var email: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime) {
    constructor(vpnUser: VpnUser): this(vpnUser.uid, vpnUser.nickname, vpnUser.status, vpnUser.email, vpnUser.createdAt, vpnUser.updatedAt)
}
