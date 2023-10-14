package ovpn.closedadmin.server.business.account.usecase

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ovpn.closedadmin.server.business.account.entity.VpnUserEntity
import ovpn.closedadmin.server.business.account.repository.VpnUserRepository
import ovpn.closedadmin.server.business.account.service.VpnUserService
import ovpn.closedadmin.server.business.account.vo.VpnUser
import ovpn.closedadmin.server.common.problem.AlreadyExistsProblem
import ovpn.closedadmin.server.common.util.Encrypt
import java.util.*

@Component
class CreateNewVpnUser @Autowired constructor(private var vpnUserRepository: VpnUserRepository, private var vpnUserService: VpnUserService) {
    fun createNewVpnUser(nickname: String, email: String, purePassword: String): VpnUser {

        var checkAlreadyExists = vpnUserRepository.countVpnUserEntitiesByEmail(email) > 0

        if (checkAlreadyExists) {
            throw AlreadyExistsProblem("VpnUser", "Vpn User Already Exists.")
        }

        var tempUUID = UUID.randomUUID()
        vpnUserRepository.save(
            VpnUserEntity(
                tempUUID,
                nickname,
                email,
                "SHA512\$"+ Encrypt.toSHA512(purePassword+tempUUID.toString())
            )
        )

        return vpnUserService.getVpnUserByUid(tempUUID)
    }
}