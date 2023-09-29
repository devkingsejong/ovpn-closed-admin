package ovpn.closedadmin.server.business.password.encrypter

interface BasePasswordEncrypter {
    fun encrypt(password: String, salt: String): String
}
