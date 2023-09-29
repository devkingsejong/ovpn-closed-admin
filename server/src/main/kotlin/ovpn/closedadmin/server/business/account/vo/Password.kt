package ovpn.closedadmin.server.business.account.vo

import ovpn.closedadmin.server.business.account.exception.InvaliedPasswordVOException

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

    constructor(passwordEncrypter: String, hashedPassword: String) : this("$passwordEncrypter$$hashedPassword")

    override fun toString(): String {
        return hashedPasswordWithEncryptedType
    }
}
