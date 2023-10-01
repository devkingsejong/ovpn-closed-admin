package ovpn.closedadmin.server.common.problem

import org.springframework.http.HttpStatus

class InvalidParameterProblem(parameterName: String, wrongReason: String) : Problem(HttpStatus.BAD_REQUEST, "InvalidParameterProblem", "Your $parameterName input is wrong. $wrongReason")
