package ovpn.closedadmin.server.common.util

import java.io.File
import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean

object SystemStatus {
    fun getSystemFreeMomory(): Double {
        val usedMemory =  Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
        return usedMemory.toDouble() / (1024 * 1024 * 1024)
    }

    @Deprecated("For test only. ")
    fun getFreeSpace(): Double {
        return File("/").getFreeSpace().toDouble() / (1024 * 1024 * 1024)
    }

    fun getCpuUsages(): Double {
        val osBean = ManagementFactory.getPlatformMXBean(
            OperatingSystemMXBean::class.java
        )
        return osBean.systemLoadAverage
    }
}
