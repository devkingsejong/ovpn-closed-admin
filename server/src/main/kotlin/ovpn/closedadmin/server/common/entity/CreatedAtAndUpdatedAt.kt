package ovpn.closedadmin.server.common.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.time.ZoneOffset
import org.hibernate.annotations.UpdateTimestamp

@MappedSuperclass
open class CreatedAtAndUpdatedAt {
    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt = LocalDateTime.now(ZoneOffset.UTC)

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt = LocalDateTime.now(ZoneOffset.UTC)
}