package ovpn.closedadmin.server.business.vpn.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ovpn.closedadmin.server.business.account.vo.VpnUser
import ovpn.closedadmin.server.business.admin.vo.Admin
import ovpn.closedadmin.server.business.vpn.usecase.GetOvpnFile
import ovpn.closedadmin.server.common.dto.CommonResponse
import java.util.UUID

@RestController
@RequestMapping("/myvpn")
class MyVpnController {

    @Autowired
    lateinit var getOvpnFile: GetOvpnFile

    @PostMapping("/download")
    fun downloadOvpnFileAdmin(
        @RequestHeader(name = "VpnAuthentication") vpnUser: VpnUser,
        ): CommonResponse<String> {
            var file: String = getOvpnFile.run(vpnUser.uid.toString())
            return CommonResponse(file)
    }
}
