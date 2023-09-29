package ovpn.closedadmin.server.common.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class EncryptTest {
    @Test
    @DisplayName("SHA512 Encrypt가 정상적으로 동작하는지 테스트")
    fun testSHA512EncryptIsWorking() {
        Assertions.assertEquals(
            "1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75",
            Encrypt.toSHA512("a")
        )
        Assertions.assertEquals(
            "a609f316d8ad1752bb65f1c180911cf26626e205d4984379ee1d2b4734c1f5a021b0fc7ae022145b85928fca503423bde31790a18f5cb952fa1dabbe31d917d3",
            Encrypt.toSHA512("나는야SHA512")
        )
        Assertions.assertEquals(
            "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e",
            Encrypt.toSHA512("")
        )
    }
}
