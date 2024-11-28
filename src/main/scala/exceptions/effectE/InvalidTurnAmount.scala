package exceptions.effectE

/**
 * Exception thrown when an effect has a negative amount or 0 of remaining turns.
 *
 * @param message A description of the error.
 */
class InvalidTurnAmount(message: String) extends Exception(message)
