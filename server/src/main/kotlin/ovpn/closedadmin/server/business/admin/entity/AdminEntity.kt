package ovpn.closedadmin.server.business.admin.entity

import jakarta.persistence.*
import ovpn.closedadmin.server.business.admin.enums.AdminStatus
import ovpn.closedadmin.server.business.admin.vo.Admin
import ovpn.closedadmin.server.business.account.vo.Password
import ovpn.closedadmin.server.common.entity.CreatedAtAndUpdatedAt
import java.util.*

@Entity
class AdminEntity(
    uid: UUID,
    nickname: String,
    level: Int,
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

    @Column(name = "level")
    var level: Int = level

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: _AdminStatus = _AdminStatus.ACTIVE

    enum class _AdminStatus {
        ACTIVE, BLOCKED, DELETED
    }

    @Column(name = "email", nullable = false, length = 150)
    var email: String = email

    @Column(name = "password", nullable = false, length = 1500)
    var password: String = password

    fun toVO(): Admin {
        return Admin(
            this.id!!,
            this.uid,
            this.nickname,
            this.level,
            AdminStatus.getAdminStatusByStatusCode(this.status.toString()),
            this.email,
            Password(this.password),
            this.createdAt,
            this.updatedAt
        )
    }
}
