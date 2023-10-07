package ovpn.closedadmin.server.business.admin.problem

import ovpn.closedadmin.server.common.problem.NotFoundProblem

class AdminNotFoundProblem(wrongReason: String="") : NotFoundProblem("AdminEntity", wrongReason)
