package ovpn.closedadmin.server.common.dto

import ovpn.closedadmin.server.common.exception.Problem

class CommonErrorField(
    val code: String,
    val message: String,
    val stackTraecElement: Array<StackTraceElement>?
) {
    constructor(problem: Problem): this(problem.code, problem.message, problem.stackTrace)
    constructor(code: String, message: String, e: Throwable) : this(code, message, e.stackTrace)
    constructor(code: String, message: String): this(code, message, null)
}
