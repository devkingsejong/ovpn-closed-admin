package ovpn.closedadmin.server.business.account.enums

enum class VpnUserStatus(val statusCode: String) {
    ACTIVE("ACTIVE"),
    BLOCKED("BLOCKED"),
    DELETED("DELETED");

    companion object {
        fun getVpnUserStatusByStatusCode(statusCode: String?): VpnUserStatus {
            return values().find { it.statusCode == statusCode } ?: throw IllegalArgumentException("Invalid statusCode: $statusCode")        }
    }
}
