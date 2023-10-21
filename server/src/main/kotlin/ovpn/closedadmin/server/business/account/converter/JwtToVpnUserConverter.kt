package ovpn.closedadmin.server.business.admin.converter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import ovpn.closedadmin.server.business.account.service.VpnUserService
import ovpn.closedadmin.server.business.account.vo.VpnUser
import ovpn.closedadmin.server.common.util.JwtUtil

@Configuration
class JwtToVpnUserConverter @Autowired constructor(private var jwtUtil: JwtUtil, private var vpnUserService: VpnUserService): Converter<String, VpnUser> {
    override fun convert(source: String): VpnUser? {
        var jwtPayload = jwtUtil.decode(source)
        var vpnUser = vpnUserService.getVpnUserByUid(
            jwtPayload.uid
        )
        return vpnUser
    }
}
