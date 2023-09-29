package ovpn.closedadmin.server.common.problem

import org.springframework.http.HttpStatus

class NotFoundProblem : Problem(HttpStatus.NOT_FOUND, "NotFoundProblem", "Can not found that object.")
