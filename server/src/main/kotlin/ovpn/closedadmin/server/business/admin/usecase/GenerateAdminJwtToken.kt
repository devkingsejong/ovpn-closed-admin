package ovpn.closedadmin.server.business.admin.usecase

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ovpn.closedadmin.server.business.account.dto.JwtPayload
import ovpn.closedadmin.server.business.admin.dto.AdminLoginForm
import ovpn.closedadmin.server.business.admin.service.AdminAccountService
import ovpn.closedadmin.server.common.util.JwtUtil
import java.time.LocalDateTime

@Service
class GenerateAdminJwtToken @Autowired constructor(private var adminAccountService: AdminAccountService, private var jwtUtil: JwtUtil) {
    fun generateJwtToken(adminLoginForm: AdminLoginForm, expiredPlusHours: Long = 3): String {

        var adminVo = adminAccountService.getAdminByEmailAndPurePassword(
            adminLoginForm.email,
            adminLoginForm.password
        )

        return jwtUtil.generate(
            JwtPayload(
                adminVo.uid,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(expiredPlusHours)
            )
        )
    }
}
