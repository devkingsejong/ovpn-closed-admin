package ovpn.closedadmin.server.common.util

import java.math.BigInteger
import java.security.MessageDigest

object Encrypt {
    fun toSHA512(input: String): String {
        val messageDigest = MessageDigest.getInstance("SHA-512")
        messageDigest.update(input.toByteArray())
        val digest = messageDigest.digest()
        return BigInteger(1, digest).toString(16).padStart(128, '0')
    }
}
