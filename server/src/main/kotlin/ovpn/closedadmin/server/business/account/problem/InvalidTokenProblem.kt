package ovpn.closedadmin.server.business.account.problem

import org.springframework.http.HttpStatus
import ovpn.closedadmin.server.common.problem.Problem

open class InvalidTokenProblem : Problem(HttpStatus.UNAUTHORIZED, "InvalidTokenProblem", "Your Token is invalid. Please check your token again.")
