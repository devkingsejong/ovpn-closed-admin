package ovpn.closedadmin.server.business.account.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ovpn.closedadmin.server.business.account.entity.VpnUserEntity
import ovpn.closedadmin.server.business.account.problem.LoginFailedProblem
import ovpn.closedadmin.server.business.account.problem.VpnUserNotFoundProblem
import ovpn.closedadmin.server.business.account.repository.VpnUserRepository
import ovpn.closedadmin.server.business.account.usecase.GetEncryptedPasswordString
import ovpn.closedadmin.server.business.account.vo.Password
import ovpn.closedadmin.server.business.account.vo.VpnUser
import java.util.*

@Service
class VpnUserServiceImpl @Autowired constructor(private var vpnUserRepository: VpnUserRepository, private var getEncryptedPasswordString: GetEncryptedPasswordString): VpnUserService {

    override fun getVpnUserByEmailAndPurePassword(email: String, purePassword: String): VpnUser {
        var tempVpnUser = this.getVpnUserByEmail(email)

        var hashedPasswordVO = Password(
            tempVpnUser.password.passwordEncrypter,
            getEncryptedPasswordString.getEncryptedPassword(
                tempVpnUser.password.passwordEncrypter,
                purePassword,
                tempVpnUser.uid.toString()
            )
        )

        var loginUser = vpnUserRepository.getVpnUserEntityByEmailAndPasswordAndStatus(
            email,
            hashedPasswordVO.hashedPasswordWithEncryptedType,
            VpnUserEntity._VpnUserStatus.ACTIVE
        ) ?: throw LoginFailedProblem()
        return loginUser.toVO()
    }

    override fun getVpnUserByEmail(email: String): VpnUser {
        var vpnUser = vpnUserRepository.getVpnUserRepositoryByEmail(email) ?: throw VpnUserNotFoundProblem()
        return vpnUser.toVO()
    }

    override fun getVpnUserByUid(uid: UUID): VpnUser {
        var vpnUser = vpnUserRepository.getVpnUserRepositoryByUid(uid) ?: throw VpnUserNotFoundProblem()
        return vpnUser.toVO()
    }
}
