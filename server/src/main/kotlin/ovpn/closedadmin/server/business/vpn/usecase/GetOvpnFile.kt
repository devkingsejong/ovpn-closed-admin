package ovpn.closedadmin.server.business.vpn.usecase

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ovpn.closedadmin.server.common.problem.NotFoundProblem
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Paths

@Service
class GetOvpnFile {

    @Value("\${openvpn.client.config.path}")
    lateinit var ovpnClientConfigPath: String

    fun run(userName: String): String {
        try {
            return String(Files.readAllBytes(Paths.get("$ovpnClientConfigPath/files/$userName.ovpn")))
        } catch (exception: NoSuchFileException) {
            throw NotFoundProblem("ovpnfile", ".ovpn file not found")
        }
    }
}
