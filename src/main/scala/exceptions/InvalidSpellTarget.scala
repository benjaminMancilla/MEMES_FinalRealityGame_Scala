package exceptions

/**
 * "Exception thrown when a spell is cast on an invalid target (defensive spell on an Enemy or offensive on a Character."
 *
 * @param message A description of the error.
 */
class InvalidSpellTarget(message: String) extends Exception(message)
