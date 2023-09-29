package ovpn.closedadmin.server.business.password.usecase

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ovpn.closedadmin.server.business.password.exception.InvaliedPasswordVOException
import ovpn.closedadmin.server.business.password.vo.Password

@SpringBootTest
internal class GetEncryptedPasswordStringTest {

    @Autowired
    lateinit var getEncryptedPasswordString: GetEncryptedPasswordString

    @Test
    @DisplayName("getEncryptedPasswordString함수가 정상적으로 hash된 비밀번호를 반환하는지 테스트")
    fun testGetEncryptedPasswordStringSuccessful() {

        //asdf에 대한 SHA512 결과를 반환하여야 함.
        Assertions.assertEquals(
            getEncryptedPasswordString.getEncryptedPassword(
                "SHA512",
                "as",
                "df"
            ),
            "401b09eab3c013d4ca54922bb802bec8fd5318192b0a75f201d8b3727429080fb337591abd3e44453b954555b7a0812e1081c39b740293f765eae731f5a65ed1"
        )

        //NoSuchBeanDefinitionException 결과를 반환하여야 함. (SHA513이라는 Encrypter 미정의)
        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
            Assertions.assertEquals(
                getEncryptedPasswordString.getEncryptedPassword(
                    "SHA513",
                    "as",
                    "df"
                ),
                "401b09eab3c013d4ca54922bb802bec8fd5318192b0a75f201d8b3727429080fb337591abd3e44453b954555b7a0812e1081c39b740293f765eae731f5a65ed1"
            )
        }

    }
}
