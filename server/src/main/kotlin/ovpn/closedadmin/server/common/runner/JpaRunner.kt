package ovpn.closedadmin.server.common.runner

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ovpn.closedadmin.server.business.account.dto.CreateVpnUserForm
import ovpn.closedadmin.server.business.account.usecase.CreateNewVpnUserWithCreateNewOvpnFile
import ovpn.closedadmin.server.business.admin.entity.AdminEntity
import ovpn.closedadmin.server.business.admin.repository.AdminRepository
import ovpn.closedadmin.server.common.constant.ReferenceValues
import ovpn.closedadmin.server.common.util.Encrypt
import java.util.*

@Component
class JpaRunner @Autowired constructor(private var adminRepository: AdminRepository, private var createNewVpnUserWithCreateNewOvpnFile: CreateNewVpnUserWithCreateNewOvpnFile) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        // create user using Password "test"
        // password rule like purepassword+salt
        // TODO: Generate Default user with JVMOps
        val tempUUID = UUID.randomUUID()
        val tempUser = AdminEntity(
            tempUUID,
            "default Admin",
            5,
            "admin@admin",
            "SHA512\$"+Encrypt.toSHA512("test"+tempUUID.toString())
        );
        adminRepository.save(tempUser)

        createNewVpnUserWithCreateNewOvpnFile.run(
            CreateVpnUserForm(
                "default vpn user",
                ReferenceValues.DEFAULT_VPN_USER_EMAIL,
                ReferenceValues.DEFAULT_VPN_USER_PASSWORD

            )
        )
    }
}
