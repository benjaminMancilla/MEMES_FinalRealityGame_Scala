package exceptions

/**
 * Exception thrown when an invalid weapon operation is attempted.
 *
 * @param message A description of the error.
 */
class InvalidWeaponException(message: String) extends Exception(message)

