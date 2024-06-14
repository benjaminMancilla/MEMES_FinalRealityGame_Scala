package exceptions.magicE

/**
 * Exception thrown when a MagicCharacter tries to cast a spell with a not magicE weaponE.
 *
 * @param message A description of the error.
 */
class NonMagicWeaponException(message: String) extends Exception(message)
