package exceptions.magic

/**
 * Exception thrown when a MagicCharacter tries to cast an spell with not enough mana.
 *
 * @param message A description of the error.
 */
class NoMagicPoints(message: String) extends Exception(message)
