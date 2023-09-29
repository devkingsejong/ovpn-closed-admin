package ovpn.closedadmin.server.business.account.exception

class InvaliedPasswordVOException(message: String = "hashedPasswordWithEncryptedType should be {hashAlgorithm\$hashedPassword}"): Exception(message) {
}
