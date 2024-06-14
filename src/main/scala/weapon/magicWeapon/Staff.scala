package weapon.magicWeapon

import entity.character._
import entity.character.commonCharacter.{Ninja, Paladin, Warrior}
import entity.character.magicCharacter.{BlackMage, WhiteMage}

/**
 * Represents a staff weaponE in the game.
 *
 * The `Staff` class extends the `AbstractMagicWeapon` class and provides specific implementations
 * for the staff's characteristics and the characters that can equip it.
 *
 * @constructor Creates a new `Staff` with the specified name, attack points, weight, and magicE attack.
 * @param name The name of the staff.
 * @param attackPoints The attack points of the staff.
 * @param weight The weight of the staff.
 * @param magicAttack The magicE attack points of the staff.
 */
class Staff(name: String, attackPoints: Int, weight: Int, magicAttack: Int)
  extends AbstractMagicWeapon(name, attackPoints, weight, magicAttack) {

  /**
   * Determines if the staff can be equipped by a Warrior.
   *
   * @param character The Warrior character attempting to equip the staff.
   * @return `false` as Warriors cannot equip staffs.
   */
  def canBeEquipped(character: Warrior): Boolean = false

  /**
   * Determines if the staff can be equipped by a Paladin.
   *
   * @param character The Paladin character attempting to equip the staff.
   * @return `false` as Paladins cannot equip staffs.
   */
  def canBeEquipped(character: Paladin): Boolean = false

  /**
   * Determines if the staff can be equipped by a Ninja.
   *
   * @param character The Ninja character attempting to equip the staff.
   * @return `false` as Ninjas cannot equip staffs.
   */
  def canBeEquipped(character: Ninja): Boolean = false

  /**
   * Determines if the staff can be equipped by a WhiteMage.
   *
   * @param character The WhiteMage character attempting to equip the staff.
   * @return `true` as WhiteMages can equip staffs.
   */
  def canBeEquipped(character: WhiteMage): Boolean = true

  /**
   * Determines if the staff can be equipped by a BlackMage.
   *
   * @param character The BlackMage character attempting to equip the staff.
   * @return `true` as BlackMages can equip staffs.
   */
  def canBeEquipped(character: BlackMage): Boolean = true

}

