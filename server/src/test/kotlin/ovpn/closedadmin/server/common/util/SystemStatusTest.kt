package ovpn.closedadmin.server.common.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SystemStatusTest {

    @Test
    fun getSystemFreeMomory() {
        Assertions.assertDoesNotThrow() {
            SystemStatus.getSystemFreeMomory()
        }
    }

    @Test
    fun getFreeSpace() {
        Assertions.assertDoesNotThrow() {
            SystemStatus.getFreeSpace()
        }
    }

    @Test
    fun getCpuUsages() {
        Assertions.assertDoesNotThrow() {
            SystemStatus.getCpuUsages()
        }
    }
}
