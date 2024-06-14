package exceptions.entityE

/**
 * Exception thrown when there are to many or to few entities in combat.
 *
 * @param message A description of the error.
 */
class EntityOverflow(message: String) extends Exception(message)