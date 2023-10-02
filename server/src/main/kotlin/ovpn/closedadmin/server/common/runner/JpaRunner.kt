package ovpn.closedadmin.server.common.runner

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ovpn.closedadmin.server.business.admin.entity.AdminEntity
import ovpn.closedadmin.server.business.admin.repository.AdminRepository
import ovpn.closedadmin.server.common.util.Encrypt
import java.util.*

@Component
class JpaRunner @Autowired constructor(private var adminRepository: AdminRepository) : CommandLineRunner {

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
    }
}
