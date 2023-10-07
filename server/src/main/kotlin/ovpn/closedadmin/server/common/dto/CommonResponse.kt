package ovpn.closedadmin.server.common.dto

import org.springframework.http.HttpStatus
import ovpn.closedadmin.server.common.problem.Problem
import java.lang.Exception

class CommonResponse<T>(
    val success: Boolean,
    val response: T?,
    val error: CommonErrorField?
) {
    constructor(response: T) : this(true, response, null)
    constructor(problem: Problem) : this(false, null, CommonErrorField(problem))

    constructor(exception: Exception) : this(false, null, CommonErrorField(exception.toString(), exception.message?: ""))

}
