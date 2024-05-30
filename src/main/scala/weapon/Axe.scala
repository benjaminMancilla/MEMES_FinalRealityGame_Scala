package weapon

import entity.character.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}

/**
 * Represents an axe weapon in the game.
 *
 * The `Axe` class extends the `AbstractWeapon` class and provides specific implementations
 * for the axe's characteristics and the characters that can equip it.
 *
 * @constructor Creates a new `Axe` with the specified name, attack points, and weight.
 * @param nameI The name of the axe.
 * @param attackPointsI The attack points of the axe.
 * @param weightI The weight of the axe.
 */
class Axe(nameI: String, attackPointsI: Int, weightI: Int)
  extends AbstractWeapon(nameI, attackPointsI, weightI) {

  /**
   * Determines if the axe can be equipped by a Warrior.
   *
   * @param character The Warrior character attempting to equip the axe.
   * @return `true` as Warriors can equip axes.
   */
  def canBeEquipped(character: Warrior): Boolean = true

  /**
   * Determines if the axe can be equipped by a Paladin.
   *
   * @param character The Paladin character attempting to equip the axe.
   * @return `true` as Paladins can equip axes.
   */
  def canBeEquipped(character: Paladin): Boolean = true

  /**
   * Determines if the axe can be equipped by a Ninja.
   *
   * @param character The Ninja character attempting to equip the axe.
   * @return `false` as Ninjas cannot equip axes.
   */
  def canBeEquipped(character: Ninja): Boolean = false

  /**
   * Determines if the axe can be equipped by a WhiteMage.
   *
   * @param character The WhiteMage character attempting to equip the axe.
   * @return `false` as WhiteMages cannot equip axes.
   */
  def canBeEquipped(character: WhiteMage): Boolean = false

  /**
   * Determines if the axe can be equipped by a BlackMage.
   *
   * @param character The BlackMage character attempting to equip the axe.
   * @return `false` as BlackMages cannot equip axes.
   */
  def canBeEquipped(character: BlackMage): Boolean = false

}

