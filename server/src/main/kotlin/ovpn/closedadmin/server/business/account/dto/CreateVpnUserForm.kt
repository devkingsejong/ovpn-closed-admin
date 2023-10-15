package ovpn.closedadmin.server.business.account.dto

data class CreateVpnUserForm(val nickname: String, val email: String, val purePassword: String)
