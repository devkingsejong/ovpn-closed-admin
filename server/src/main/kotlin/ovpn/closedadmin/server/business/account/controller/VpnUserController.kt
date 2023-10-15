package ovpn.closedadmin.server.business.account.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ovpn.closedadmin.server.business.account.dto.CreateVpnUserForm
import ovpn.closedadmin.server.business.account.usecase.CreateNewVpnUserWithCreateNewOvpnFile
import ovpn.closedadmin.server.business.admin.vo.Admin
import ovpn.closedadmin.server.common.dto.CommonResponse

@RestController
@RequestMapping("/vpnuser")
class VpnUserController {

    @Autowired
    lateinit var createNewVpnUserWithCreateNewOvpnFile: CreateNewVpnUserWithCreateNewOvpnFile

    @PostMapping("/create")
    fun createNewVpnUser(
        @RequestHeader(name = "Authentication") admin: Admin,
        @RequestBody createVpnUserForm: CreateVpnUserForm
        ): CommonResponse<String> {
            var ovpnFile = createNewVpnUserWithCreateNewOvpnFile.run(createVpnUserForm)
            return CommonResponse(ovpnFile)
    }
}
