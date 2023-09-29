package ovpn.closedadmin.server.business.account.encrypter

interface BasePasswordEncrypter {
    fun encrypt(password: String, salt: String): String
}
