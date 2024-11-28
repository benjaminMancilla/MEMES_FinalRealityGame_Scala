package exceptions.stateE

/**
 * Exception thrown when a weapon inventory has not enough weapons for the party.
 *
 * @param message A description of the error.
 */
class InvalidWeaponInventory(message: String) extends Exception(message)
