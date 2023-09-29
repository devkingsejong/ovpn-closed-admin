package ovpn.closedadmin.server.business.account.usecase

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component
import ovpn.closedadmin.server.business.account.encrypter.BasePasswordEncrypter
import ovpn.closedadmin.server.common.constant.CommonConstant

@Component
class GetEncryptedPasswordString {
    fun getEncryptedPassword(passwordEncrypter: String, purePassword: String, salt: String): String{
        val context = AnnotationConfigApplicationContext()
        context.scan("ovpn.closedadmin.server.business.account.encrypter")
        context.refresh()

        val targetPasswordEncrypter: BasePasswordEncrypter = context.getBean(
            CommonConstant.PASSWORD_ENCRYPTER_PREFIX + passwordEncrypter
        ) as BasePasswordEncrypter

        return targetPasswordEncrypter.encrypt(purePassword, salt)
    }
}
