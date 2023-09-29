package ovpn.closedadmin.server.common.dto

import ovpn.closedadmin.server.common.problem.Problem

class CommonResponse<T>(
    val success: Boolean,
    val response: T?,
    val error: CommonErrorField?
) {
    constructor(response: T) : this(true, response, null)
    constructor(problem: Problem) : this(false, null, CommonErrorField(problem))
}
