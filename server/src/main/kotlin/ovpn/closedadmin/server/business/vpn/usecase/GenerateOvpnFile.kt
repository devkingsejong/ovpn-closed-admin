package ovpn.closedadmin.server.business.vpn.usecase

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths

@Service
class GenerateOvpnFile{

    @Value("\${openvpn.client.config.path}")
    lateinit var ovpnClientConfigPath: String

    fun generateOvpnFile(userName: String): String {
        val cmd = arrayOf("/bin/bash", "$ovpnClientConfigPath/make_config.sh", userName)
        Runtime.getRuntime().exec(cmd)
        return String(Files.readAllBytes(Paths.get("$ovpnClientConfigPath/files/$userName.ovpn")));
    }

}
