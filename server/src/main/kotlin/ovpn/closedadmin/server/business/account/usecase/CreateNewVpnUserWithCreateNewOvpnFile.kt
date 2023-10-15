package ovpn.closedadmin.server.business.account.usecase

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ovpn.closedadmin.server.business.account.dto.CreateVpnUserForm
import ovpn.closedadmin.server.business.vpn.usecase.GenerateOvpnFile
import ovpn.closedadmin.server.business.vpn.usecase.SignNewUser

@Component
class CreateNewVpnUserWithCreateNewOvpnFile
    @Autowired constructor(private var createNewVpnUser: CreateNewVpnUser, private var generateOvpnFile: GenerateOvpnFile, private var signNewUser: SignNewUser) {
        fun run(createVpnUserForm: CreateVpnUserForm): String {
            var createdUser = createNewVpnUser.createNewVpnUser(createVpnUserForm.nickname, createVpnUserForm.email, createVpnUserForm.purePassword)
            signNewUser.signNewUser(createdUser.uid.toString())
            var ovpnFile = generateOvpnFile.generateOvpnFile(createdUser.uid.toString())
            return ovpnFile
        }
}
