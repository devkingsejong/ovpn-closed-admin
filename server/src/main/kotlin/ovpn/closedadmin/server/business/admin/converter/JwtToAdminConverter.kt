package ovpn.closedadmin.server.business.admin.converter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import ovpn.closedadmin.server.business.admin.service.AdminAccountService
import ovpn.closedadmin.server.business.admin.vo.Admin
import ovpn.closedadmin.server.common.util.JwtUtil

@Configuration
class JwtToAdminConverter @Autowired constructor(private var jwtUtil: JwtUtil, private var adminAccountService: AdminAccountService): Converter<String, Admin> {
    override fun convert(source: String): Admin? {
        var jwtPayload = jwtUtil.decode(source)
        var admin = adminAccountService.getAdminByUid(
            jwtPayload.uid
        )
        return admin
    }
}
