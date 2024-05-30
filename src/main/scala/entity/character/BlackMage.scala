package entity.character

import magic.Magic
import weapon.Weapon

/**
 * Represents a Black Mage entity in the game, specializing in offensive magic.
 *
 * The `BlackMage` class extends the `AbstractMagicCharacter` class and represents a character
 * with abilities to cast offensive spells. It inherits properties and behaviors from its superclass
 * and implements additional functionalities specific to black mages.
 *
 * @param nameI The name of the black mage.
 * @param hit_pointsI The initial hit points of the black mage.
 * @param defenseI The defense level of the black mage.
 * @param weightI The weight of the black mage.
 * @param magic_pointsI The total magic points of the black mage.
 */
class BlackMage(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractMagicCharacter(nameI, hit_pointsI, defenseI, weightI, magic_pointsI) {

  /** The type name of the black mage. */
  val _typeName: String = "BlackMage"

  /**
   * Checks if a given weapon can be equipped by the black mage.
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

