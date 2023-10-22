package ovpn.closedadmin.server.business.account.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ovpn.closedadmin.server.business.account.dto.TokenInfo
import ovpn.closedadmin.server.business.account.dto.VpnUserLoginForm
import ovpn.closedadmin.server.business.account.usecase.GenerateVpnUserJwtToken
import ovpn.closedadmin.server.business.account.vo.VpnUser
import ovpn.closedadmin.server.common.dto.CommonResponse

@RestController
@RequestMapping("/vpnuser/login")
class VpnUserLoginController {

    @Autowired
    lateinit var generateVpnUserJwtToken: GenerateVpnUserJwtToken

    @PostMapping("/auth")
    fun generateAdminToken(@RequestBody vpnUserLoginForm: VpnUserLoginForm): CommonResponse<TokenInfo> {
        val jwtToken = generateVpnUserJwtToken.generateJwtToken(vpnUserLoginForm)
        return CommonResponse(TokenInfo(jwtToken))
    }

//    Thanks to [JwtToVpnUserConverter],
//    You just get VpnUser Vo Like @RequestHeader(name = "vpnUserLoginForm") vpnUser: VpnUser
//    JwtToVpnUserConverter Converts to JWT Token to Admin VO
    @PostMapping("/check")
    fun checkMyToken(@RequestHeader(name = "VpnAuthentication") vpnUser: VpnUser): CommonResponse<String> {
        return CommonResponse("Hi, ${vpnUser.nickname}. Welcome to OVPN-CLOSED-ADMIN")
    }
}
