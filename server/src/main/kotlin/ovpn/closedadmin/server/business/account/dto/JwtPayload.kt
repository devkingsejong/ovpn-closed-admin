package ovpn.closedadmin.server.business.account.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import ovpn.closedadmin.server.common.deserializer.UnixTimestampToLocalDateTime
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

data class JwtPayload(
    var uid: UUID,
    @JsonDeserialize(using = UnixTimestampToLocalDateTime::class) var iat: LocalDateTime,
    @JsonDeserialize(using = UnixTimestampToLocalDateTime::class) var exp: LocalDateTime
)
