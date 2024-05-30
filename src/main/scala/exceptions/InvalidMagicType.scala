package exceptions

/**
 * "An exception is thrown when a MagicCharacter tries to use a MagicType that is not compatible with their own."
 *
 * @param message A description of the error.
 */
class InvalidMagicType(message: String) extends Exception(message)
