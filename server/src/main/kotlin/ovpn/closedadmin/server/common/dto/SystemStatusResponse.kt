package ovpn.closedadmin.server.common.dto

data class SystemStatusResponse (
    val freeMemory: Double,
    val freeSpace: Double,
    val systemLoadAverage: Double
)
