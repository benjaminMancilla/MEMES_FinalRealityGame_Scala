package exceptions.weaponE

/**
 * Exception thrown when an entity that can not have weapons tries to equip one.
 *
 * @param message A description of the error.
 */
class InvalidCarrier(message: String) extends Exception(message)
