package ovpn.closedadmin.server.business.password.exception

class InvaliedPasswordVOException(message: String = "hashedPasswordWithEncryptedType should be {hashAlgorithm\$hashedPassword}"): Exception(message) {
}
