package ovpn.closedadmin.server.business.account.problem

import ovpn.closedadmin.server.common.problem.NotFoundProblem

class VpnUserNotFoundProblem(wrongReason: String="") : NotFoundProblem("AdminEntity", wrongReason)
