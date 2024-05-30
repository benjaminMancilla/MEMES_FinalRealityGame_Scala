package entity.character

import weapon.Weapon

/**
 * Represents a Warrior entity in the game, known for its strength and combat prowess.
 *
 * The `Warrior` class extends the `AbstractCharacter` class and represents a character with warrior-like abilities.
 * It inherits properties and behaviors from its superclass and implements additional functionalities specific to warriors.
 *
 * @param nameI The name of the warrior.
 * @param hit_pointsI The initial hit points of the warrior.
 * @param defenseI The defense level of the warrior.
 * @param weightI The weight of the warrior.
 */
class Warrior(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractCharacter(nameI, hit_pointsI, defenseI, weightI) {

  /** The type name of the warrior. */
  val _typeName: String = "Warrior"

  /**
   * Checks if a given weapon can be equipped by the warrior.
   *
   * @param newWeapon The new weapon to be checked.
   * @return `true` if the weapon can be equipped, `false` otherwise.
   */
  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean = {
    newWeapon.exists(_.canBeEquipped(this))
  }
}
