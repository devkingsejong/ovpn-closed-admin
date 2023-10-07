package ovpn.closedadmin.server.common.problem

import org.springframework.http.HttpStatus

open class NotFoundProblem(parameterName: String="", wrongReason: String="") : Problem(HttpStatus.NOT_FOUND, "NotFoundProblem", "Can not found $parameterName [$wrongReason]")
