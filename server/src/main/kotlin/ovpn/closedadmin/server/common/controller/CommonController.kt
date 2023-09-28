package ovpn.closedadmin.server.common.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ovpn.closedadmin.server.common.dto.CommonResponse
import ovpn.closedadmin.server.common.exception.NotFoundProblem

@RestController
class CommonController {

    @GetMapping("/")
    fun hello(): CommonResponse<String> {
        return CommonResponse("Hello! This is OVPN-CLOSED-ADMIN-SERVER.")
    }

    @GetMapping("/404")
    fun notfound(): CommonResponse<String> {
        throw NotFoundProblem()
    }
}
