package exceptions.stateE

/**
 * Exception thrown when an invalid entity is in the spell menu.
 *
 * @param message A description of the error.
 */
class InvalidSpellSelector(message: String) extends Exception(message)
