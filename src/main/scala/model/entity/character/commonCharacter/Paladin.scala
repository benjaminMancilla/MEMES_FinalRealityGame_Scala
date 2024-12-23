package model.entity.character.commonCharacter

import model.entity.character.AbstractCharacter
import model.weapon.Weapon

/**
 * Represents a Paladin entityE in the game, known for its strength and righteousness.
 *
 * The `Paladin` class extends the `AbstractCharacter` class and represents a character with paladin-like abilities.
 * It inherits properties and behaviors from its superclass and implements additional functionalities specific to paladins.
 *
 * @param nameI The name of the paladin.
 * @param hit_pointsI The initial hit points of the paladin.
 * @param defenseI The defense level of the paladin.
 * @param weightI The weight of the paladin.
 */
class Paladin(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractCommonCharacter(nameI, hit_pointsI, defenseI, weightI) {

  /** The type name of the paladin. */
  val _typeName: String = "Paladin"

  /**
   * Checks if a given weaponE can be equipped by the paladin.
   *
   * @param newWeapon The new weaponE to be checked.
   * @return `true` if the weaponE can be equipped, `false` otherwise.
   */
  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean = {
    newWeapon.exists(_.canBeEquipped(this))
  }
}
