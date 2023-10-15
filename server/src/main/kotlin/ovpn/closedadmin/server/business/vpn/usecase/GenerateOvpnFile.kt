package ovpn.closedadmin.server.business.vpn.usecase

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GenerateOvpnFile @Autowired constructor(private var getOvpnFile: GetOvpnFile){

    @Value("\${openvpn.client.config.path}")
    lateinit var ovpnClientConfigPath: String

    fun generateOvpnFile(userName: String): String {
        val cmd = arrayOf("/bin/bash", "$ovpnClientConfigPath/make_config.sh", userName)
        val process = Runtime.getRuntime().exec(cmd)
        val exitCode = process.waitFor()
        Runtime.getRuntime().exec(cmd)
        return getOvpnFile.run(userName)
    }

}
