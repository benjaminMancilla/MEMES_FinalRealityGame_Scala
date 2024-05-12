package weapon

import entity.character.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}

/**
 * Class representing an empty weapon.
 * An empty weapon is used to represent being unarmed.
 *
 * @param name         The name of the empty weapon.
 * @param attackPoints The attack points of the empty weapon.
 * @param weight       The weight of the empty weapon.
 */
class EmptyWeapon(name: String = "EmptyWeapon", attackPoints: Int = 0, weight: Int = 0)
  extends AbstractWeapon(name: String, attackPoints: Int, weight: Int) {

  def canBeEquipped(character: Warrior): Boolean = true
  def canBeEquipped(character: Paladin): Boolean = true
  def canBeEquipped(character: Ninja): Boolean = true
  def canBeEquipped(character: WhiteMage): Boolean = true
  def canBeEquipped(character: BlackMage): Boolean = true

  override def isEmptyWeapon: Boolean = true

}
