package ovpn.closedadmin.server.business.account.repository

import org.springframework.data.jpa.repository.JpaRepository
import ovpn.closedadmin.server.business.account.entity.VpnUserEntity
import java.util.*

interface VpnUserRepository: JpaRepository<VpnUserEntity, Long> {
    fun getVpnUserRepositoryByUid(uid: UUID): VpnUserEntity?
    fun getVpnUserRepositoryByEmail(email: String): VpnUserEntity?
    fun getVpnUserEntityByEmailAndPasswordAndStatus(email: String, password: String, status: VpnUserEntity._VpnUserStatus): VpnUserEntity?
}
