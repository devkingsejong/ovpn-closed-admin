package ovpn.closedadmin.server.business.admin.enums

enum class AdminStatus(val statusCode: String) {
    ACTIVE("ACTIVE"),
    BLOCKED("BLOCKED"),
    DELETED("DELETED");

    companion object {
        fun getAdminStatusByStatusCode(statusCode: String?): AdminStatus {
            return values().find { it.statusCode == statusCode } ?: throw IllegalArgumentException("Invalid statusCode: $statusCode")        }
    }
}
