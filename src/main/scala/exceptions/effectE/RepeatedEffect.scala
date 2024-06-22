package exceptions.effectE

/**
 * Exception thrown when an effect tries to be added to an entity that already has it.
 *
 * @param message A description of the error.
 */
class RepeatedEffect(message: String) extends Exception(message)
