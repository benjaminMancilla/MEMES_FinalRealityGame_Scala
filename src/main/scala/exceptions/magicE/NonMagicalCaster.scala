package exceptions.magicE

/**
 * Exception thrown when a non MagicCharacter tries to use magic.
 *
 * @param message A description of the error.
 */
class NonMagicalCaster(message: String) extends Exception(message)