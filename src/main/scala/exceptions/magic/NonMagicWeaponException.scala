package exceptions.magic

/**
 * Exception thrown when a MagicCharacter tries to cast a spell with a not magic weapon.
 *
 * @param message A description of the error.
 */
class NonMagicWeaponException(message: String) extends Exception(message)
