package model.weapon.commonWeapon

import model.entity.character.commonCharacter.{Ninja, Paladin, Warrior}
import model.entity.character.magicCharacter.{BlackMage, WhiteMage}
import model.weapon.AbstractWeapon

/**
 * Represents a bow weaponE in the game.
 *
 * The `Bow` class extends the `AbstractWeapon` class and provides specific implementations
 * for the bow's characteristics and the characters that can equip it.
 *
 * @constructor Creates a new `Bow` with the specified name, attack points, and weight.
 * @param nameI The name of the bow.
 * @param attackPointsI The attack points of the bow.
 * @param weightI The weight of the bow.
 */
class Bow(nameI: String, attackPointsI: Int, weightI: Int)
  extends AbstractWeapon(nameI, attackPointsI, weightI) {

  /**
   * Determines if the bow can be equipped by a Warrior.
   *
   * @param character The Warrior character attempting to equip the bow.
   * @return `true` as Warriors can equip bows.
   */
  def canBeEquipped(character: Warrior): Boolean = true

  /**
   * Determines if the bow can be equipped by a Paladin.
   *
   * @param character The Paladin character attempting to equip the bow.
   * @return `false` as Paladins cannot equip bows.
   */
  def canBeEquipped(character: Paladin): Boolean = false

  /**
   * Determines if the bow can be equipped by a Ninja.
   *
   * @param character The Ninja character attempting to equip the bow.
   * @return `true` as Ninjas can equip bows.
   */
  def canBeEquipped(character: Ninja): Boolean = true

  /**
   * Determines if the bow can be equipped by a WhiteMage.
   *
   * @param character The WhiteMage character attempting to equip the bow.
   * @return `true` as WhiteMages can equip bows.
   */
  def canBeEquipped(character: WhiteMage): Boolean = true

  /**
   * Determines if the bow can be equipped by a BlackMage.
   *
   * @param character The BlackMage character attempting to equip the bow.
   * @return `false` as BlackMages cannot equip bows.
   */
  def canBeEquipped(character: BlackMage): Boolean = false

}

