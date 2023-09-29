package ovpn.closedadmin.server.business.password.encrypter

import org.springframework.stereotype.Component
import ovpn.closedadmin.server.common.constant.CommonConstant
import ovpn.closedadmin.server.common.util.Encrypt

@Component(CommonConstant.PASSWORD_ENCRYPTER_PREFIX+"SHA512")
class SHA512PasswordEncrypter: BasePasswordEncrypter {
    override fun encrypt(password: String, salt: String): String {
        return Encrypt.toSHA512(password+salt)
    }
}
