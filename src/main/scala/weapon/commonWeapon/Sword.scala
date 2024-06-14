package weapon.commonWeapon

import entity.character._
import entity.character.commonCharacter.{Ninja, Paladin, Warrior}
import entity.character.magicCharacter.{BlackMage, WhiteMage}
import weapon.AbstractWeapon

/**
 * Represents a sword weaponE in the game.
 *
 * The `Sword` class extends the `AbstractWeapon` class and provides specific implementations
 * for the sword's characteristics and the characters that can equip it.
 *
 * @constructor Creates a new `Sword` with the specified name, attack points, and weight.
 * @param nameI The name of the sword.
 * @param attackPointsI The attack points of the sword.
 * @param weightI The weight of the sword.
 */
class Sword(nameI: String, attackPointsI: Int, weightI: Int)
  extends AbstractWeapon(nameI, attackPointsI, weightI) {

  /**
   * Determines if the sword can be equipped by a Warrior.
   *
   * @param character The Warrior character attempting to equip the sword.
   * @return `true` as Warriors can equip swords.
   */
  def canBeEquipped(character: Warrior): Boolean = true

  /**
   * Determines if the sword can be equipped by a Paladin.
   *
   * @param character The Paladin character attempting to equip the sword.
   * @return `true` as Paladins can equip swords.
   */
  def canBeEquipped(character: Paladin): Boolean = true

  /**
   * Determines if the sword can be equipped by a Ninja.
   *
   * @param character The Ninja character attempting to equip the sword.
   * @return `true` as Ninjas can equip swords.
   */
  def canBeEquipped(character: Ninja): Boolean = true

  /**
   * Determines if the sword can be equipped by a WhiteMage.
   *
   * @param character The WhiteMage character attempting to equip the sword.
   * @return `false` as WhiteMages cannot equip swords.
   */
  def canBeEquipped(character: WhiteMage): Boolean = false

  /**
   * Determines if the sword can be equipped by a BlackMage.
   *
   * @param character The BlackMage character attempting to equip the sword.
   * @return `true` as BlackMages can equip swords.
   */
  def canBeEquipped(character: BlackMage): Boolean = true

}

