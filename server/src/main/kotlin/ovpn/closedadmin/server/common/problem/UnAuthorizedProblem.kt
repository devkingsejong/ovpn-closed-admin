package ovpn.closedadmin.server.common.problem

import org.springframework.http.HttpStatus

open class UnAuthorizedProblem : Problem(HttpStatus.UNAUTHORIZED, "UnAuthorizedProblem", "That is UnAuthorized Job.")
