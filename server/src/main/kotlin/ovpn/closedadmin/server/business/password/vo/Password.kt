package ovpn.closedadmin.server.business.password.vo

import ovpn.closedadmin.server.business.password.exception.InvaliedPasswordVOException

// hashedPasswordWithEncrypyType: SHA512$cf83e1357eefb8bdf1542850d66d8007(.....)
data class Password (var hashedPasswordWithEncryptedType:String) {
    val passwordEncrypter: String
    val hashedPassword: String

    init {
        try {
            val splittedInputPasswordString = hashedPasswordWithEncryptedType.split("$")
            this.passwordEncrypter = splittedInputPasswordString[0]
            this.hashedPassword = splittedInputPasswordString[1]

            if(this.passwordEncrypter.isEmpty() || this.hashedPassword.isEmpty()) {
                throw Exception()
            }
        } catch (e: Exception) {
            throw InvaliedPasswordVOException()
        }

    }

    override fun toString(): String {
        return hashedPasswordWithEncryptedType
    }
}