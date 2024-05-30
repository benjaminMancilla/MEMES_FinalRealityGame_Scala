package entity.character

import magic.Magic
import weapon.Weapon

/**
 * Represents a White Mage entity in the game, known for its healing and supportive magic.
 *
 * The `WhiteMage` class extends the `AbstractMagicCharacter` class and represents a character with white mage-like abilities.
 * It inherits properties and behaviors from its superclass and implements additional functionalities specific to white mages.
 *
 * @param nameI The name of the white mage.
 * @param hit_pointsI The initial hit points of the white mage.
 * @param defenseI The defense level of the white mage.
 * @param weightI The weight of the white mage.
 * @param magic_pointsI The total magic points of the white mage.
 */
class WhiteMage(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractMagicCharacter(nameI, hit_pointsI, defenseI, weightI, magic_pointsI) {

  /** The type name of the white mage. */
  val _typeName: String = "WhiteMage"

  /**
   * Checks if a given weapon can be equipped by the white mage.
   *
   * @param newWeapon The new weapon to be checked.
   * @return `true` if the weapon can be equipped, `false` otherwise.
   */
  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean = {
    newWeapon.exists(_.canBeEquipped(this))
  }

  /**
   * Checks if the black mage can cast the given spell.
   *
   * Overrides the `checkSpell` method defined in the `AbstractMagicCharacter` class.
   *
   * @param spell The spell to be cast.
   */
  override def checkSpell(spell: Magic): Unit = {
    spell.checkSorcerer(this)
  }
}

