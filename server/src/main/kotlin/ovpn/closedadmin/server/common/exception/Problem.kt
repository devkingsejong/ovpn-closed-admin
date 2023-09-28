package ovpn.closedadmin.server.common.exception

import org.springframework.http.HttpStatus

open class Problem(val status: HttpStatus, val code: String, override val message: String) : RuntimeException(message)
