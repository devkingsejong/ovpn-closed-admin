package ovpn.closedadmin.server.business.account.usecase

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ovpn.closedadmin.server.business.account.dto.JwtPayload
import ovpn.closedadmin.server.business.account.dto.VpnUserLoginForm
import ovpn.closedadmin.server.business.account.service.VpnUserService
import ovpn.closedadmin.server.common.util.JwtUtil
import java.time.LocalDateTime

@Service
class GenerateVpnUserJwtToken @Autowired constructor(private var vpnUserService: VpnUserService, private var jwtUtil: JwtUtil) {
    fun generateJwtToken(vpnUserLoginForm: VpnUserLoginForm, expiredPlusHours: Long = 3): String {

        var vpnUser = vpnUserService.getVpnUserByEmailAndPurePassword(
            vpnUserLoginForm.email,
            vpnUserLoginForm.password
        )

        return jwtUtil.generate(
            JwtPayload(
                vpnUser.uid,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(expiredPlusHours)
            )
        )
    }
}
