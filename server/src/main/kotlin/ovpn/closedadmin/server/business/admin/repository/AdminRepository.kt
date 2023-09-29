package ovpn.closedadmin.server.business.admin.repository

import org.springframework.data.jpa.repository.JpaRepository
import ovpn.closedadmin.server.business.admin.entity.AdminEntity
import java.util.*

interface AdminRepository: JpaRepository<AdminEntity, Long> {
    fun getAdminEntityByUid(uid: UUID): AdminEntity?
    fun getAdminEntityByEmail(email: String): AdminEntity?
    fun getAdminEntityByEmailAndPasswordAndStatus(email: String, password: String, status: AdminEntity._AdminStatus): AdminEntity?
}
