package ovpn.closedadmin.server.common.problem

import org.springframework.http.HttpStatus

class AlreadyExistsProblem(parameterName: String, wrongReason: String) : Problem(HttpStatus.BAD_REQUEST, "AlreadyExistsProblem", "Your $parameterName is already exists. [$wrongReason]")
