package ovpn.closedadmin.server.business.account.entity

import jakarta.persistence.*
import ovpn.closedadmin.server.business.account.enums.VpnUserStatus
import ovpn.closedadmin.server.business.account.vo.Password
import ovpn.closedadmin.server.business.account.vo.VpnUser
import ovpn.closedadmin.server.common.entity.CreatedAtAndUpdatedAt
import java.util.*

@Entity
class VpnUserEntity(
    uid: UUID,
    nickname: String,
    email: String,
    password: String
) : CreatedAtAndUpdatedAt() {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "uid", nullable = false, unique = true)
    val uid: UUID = uid

    @Column(name = "nickname", nullable = false, length = 64)
    var nickname: String = nickname

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: _VpnUserStatus = _VpnUserStatus.ACTIVE

    enum class _VpnUserStatus {
        ACTIVE, BLOCKED, DELETED
    }

    @Column(name = "email", unique = true, nullable = false, length = 150)
    var email: String = email

    @Column(name = "password", nullable = false, length = 1500)
    var password: String = password

    fun toVO(): VpnUser {
        return VpnUser(
            this.id!!,
            this.uid,
            this.nickname,
            VpnUserStatus.getVpnUserStatusByStatusCode(this.status.toString()),
            this.email,
            Password(this.password),
            this.createdAt,
            this.updatedAt
        )
    }
}
