package exceptions.weapon

/**
 * Exception thrown when a Character tries to attack with no weapon.
 *
 * @param message A description of the error.
 */
class EmptyWeaponException(message: String) extends Exception(message)
