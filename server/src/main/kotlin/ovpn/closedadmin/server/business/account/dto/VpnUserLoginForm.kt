package ovpn.closedadmin.server.business.account.dto

import ovpn.closedadmin.server.common.problem.InvalidParameterProblem

data class VpnUserLoginForm (
    val email: String,
    val password: String
) {
    init {
        validate()
    }

    private fun validate() {
        if (email.length < 2) {
            throw InvalidParameterProblem("email", "email length must longer then 2")
        }

        if (password.isEmpty()) {
            throw InvalidParameterProblem("password", "password length must longer then 1")
        }
    }
}
