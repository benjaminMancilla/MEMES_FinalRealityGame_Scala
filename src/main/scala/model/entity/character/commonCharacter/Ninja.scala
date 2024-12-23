package model.entity.character.commonCharacter

import model.weapon.Weapon

/**
 * Represents a Ninja entityE in the game, known for its agility and stealth.
 *
 * The `Ninja` class extends the `AbstractCharacter` class and represents a character with ninja-like abilities.
 * It inherits properties and behaviors from its superclass and implements additional functionalities specific to ninjas.
 *
 * @param nameI The name of the ninja.
 * @param hit_pointsI The initial hit points of the ninja.
 * @param defenseI The defense level of the ninja.
 * @param weightI The weight of the ninja.
 */
class Ninja(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractCommonCharacter(nameI, hit_pointsI, defenseI, weightI) {

  /** The type name of the ninja. */
  val _typeName: String = "Ninja"

  /**
   * Checks if a given weaponE can be equipped by the ninja.
   *
   * @param newWeapon The new weaponE to be checked.
   * @return `true` if the weaponE can be equipped, `false` otherwise.
   */
  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean = {
    newWeapon.exists(_.canBeEquipped(this))
  }

}
