package exceptions.entityE

/**
 * Exception thrown when entityE tries to hit an ally (for enemies and characters).
 *
 * @param message A description of the error.
 */
class ProhibitedTarget (message: String) extends Exception(message)
