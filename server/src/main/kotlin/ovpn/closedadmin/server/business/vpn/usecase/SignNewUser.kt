package ovpn.closedadmin.server.business.vpn.usecase

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Service
class SignNewUser {

    @Value("\${easyrsa.path}")
    lateinit var easyRsaPath: String

    @Value("\${openvpn.client.config.path}")
    lateinit var ovpnClientConfigPath: String

    fun signNewUser(userName: String): Boolean {

        val cmd = arrayOf("/bin/sh", "$easyRsaPath/sign_new_user.sh", userName)
        Runtime.getRuntime().exec(cmd)
        return checkKeyCreated(userName) && checkCrtCreated(userName)
    }

    fun checkKeyCreated(userName: String) : Boolean {
        val path: Path = Paths.get("$ovpnClientConfigPath/keys/$userName.key")
        return Files.exists(path)
    }

    fun checkCrtCreated(userName: String) : Boolean {
        val path: Path = Paths.get("$ovpnClientConfigPath/keys/$userName.crt")
        return Files.exists(path)
    }
}
