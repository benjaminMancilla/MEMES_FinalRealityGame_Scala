package exceptions

/**
 * Exception thrown when an invalid name is given.
 *
 * @param message A description of the error.
 */
class InvalidNameException(message: String) extends Exception(message)
