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
        val process = Runtime.getRuntime().exec(cmd)
        val exitCode = process.waitFor()
        Runtime.getRuntime().exec(cmd)
        // TODO: in this time, failed to create or get file throws java.nio.file.NoSuchFileException
        return String(Files.readAllBytes(Paths.get("$ovpnClientConfigPath/files/$userName.ovpn")));
    }

}
