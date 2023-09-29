package ovpn.closedadmin.server.business.admin.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ovpn.closedadmin.server.business.admin.entity.AdminEntity
import ovpn.closedadmin.server.business.admin.repository.AdminRepository
import ovpn.closedadmin.server.business.admin.vo.Admin
import ovpn.closedadmin.server.business.account.problem.LoginFailedProblem
import ovpn.closedadmin.server.business.account.usecase.GetEncryptedPasswordString
import ovpn.closedadmin.server.business.account.vo.Password

@Service
class AdminAccountServiceImpl @Autowired constructor(private var adminRepository: AdminRepository, private var getEncryptedPasswordString: GetEncryptedPasswordString): AdminAccountService {
    override fun getAdminByEmailAndPurePassword(email: String, purePassword: String): Admin {
        var tempLoginUser = adminRepository.getAdminEntityByEmail(email) ?: throw LoginFailedProblem()
        var tempLoginUserVO = tempLoginUser.toVO()

        var hashedPasswordVO = Password(
            tempLoginUserVO.password.passwordEncrypter,
            getEncryptedPasswordString.getEncryptedPassword(
                tempLoginUserVO.password.passwordEncrypter,
                purePassword,
                tempLoginUserVO.uid.toString()
            )
        )

        var loginUser = adminRepository.getAdminEntityByEmailAndPasswordAndStatus(
            email,
            hashedPasswordVO.hashedPasswordWithEncryptedType,
            AdminEntity._AdminStatus.ACTIVE
        ) ?: throw LoginFailedProblem()
        return loginUser.toVO()
    }
}
