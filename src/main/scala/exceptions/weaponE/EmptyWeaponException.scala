package exceptions.weaponE

/**
 * Exception thrown when a Character tries to attack with no weaponE.
 *
 * @param message A description of the error.
 */
class EmptyWeaponException(message: String) extends Exception(message)
