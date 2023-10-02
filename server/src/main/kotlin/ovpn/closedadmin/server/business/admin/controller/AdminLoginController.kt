package ovpn.closedadmin.server.business.admin.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ovpn.closedadmin.server.business.account.dto.TokenInfo
import ovpn.closedadmin.server.business.admin.dto.AdminLoginForm
import ovpn.closedadmin.server.business.admin.usecase.GenerateAdminJwtToken
import ovpn.closedadmin.server.common.dto.CommonResponse

@RestController
@RequestMapping("/admin/login")
class AdminLoginController {

    @Autowired
    lateinit var generateAdminJwtToken: GenerateAdminJwtToken

    @PostMapping("/auth")
    fun generateAdminToken(@RequestBody adminLoginForm: AdminLoginForm): CommonResponse<TokenInfo> {
        val jwtToken = generateAdminJwtToken.generateJwtToken(adminLoginForm)
        return CommonResponse(TokenInfo(jwtToken))
    }
}
