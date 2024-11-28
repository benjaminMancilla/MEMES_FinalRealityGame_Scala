package exceptions.entityE

/**
 * Exception thrown when an entityE tries to heal a dead entityE
 *
 * @param message A description of the error.
 */
class HealingDeadEntity(message: String) extends Exception(message)
