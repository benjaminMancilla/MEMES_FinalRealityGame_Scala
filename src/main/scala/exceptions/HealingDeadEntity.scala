package exceptions

/**
 * Exception thrown when an entity tries to heal a dead entity
 *
 * @param message A description of the error.
 */
class HealingDeadEntity(message: String) extends Exception(message)
