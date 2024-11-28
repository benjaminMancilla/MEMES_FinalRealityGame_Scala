package model.weapon.magicWeapon

import model.entity.character.commonCharacter.{Ninja, Paladin, Warrior}
import model.entity.character.magicCharacter.{BlackMage, WhiteMage}

/**
 * Represents a wand weaponE in the game.
 *
 * The `Wand` class extends the `AbstractMagicWeapon` class and provides specific implementations
 * for the wand's characteristics and the characters that can equip it.
 *
 * @constructor Creates a new `Wand` with the specified name, attack points, weight, and magicE attack.
 * @param name The name of the wand.
 * @param attackPoints The attack points of the wand.
 * @param weight The weight of the wand.
 * @param magicAttack The magicE attack points of the wand.
 */
class Wand(name: String, attackPoints: Int, weight: Int, magicAttack: Int)
  extends AbstractMagicWeapon(name, attackPoints, weight, magicAttack) {

  /**
   * Determines if the wand can be equipped by a Warrior.
   *
   * @param character The Warrior character attempting to equip the wand.
   * @return `false` as Warriors cannot equip wands.
   */
  def canBeEquipped(character: Warrior): Boolean = false

  /**
   * Determines if the wand can be equipped by a Paladin.
   *
   * @param character The Paladin character attempting to equip the wand.
   * @return `false` as Paladins cannot equip wands.
   */
  def canBeEquipped(character: Paladin): Boolean = false

  /**
   * Determines if the wand can be equipped by a Ninja.
   *
   * @param character The Ninja character attempting to equip the wand.
   * @return `true` as Ninjas can equip wands.
   */
  def canBeEquipped(character: Ninja): Boolean = true

  /**
   * Determines if the wand can be equipped by a WhiteMage.
   *
   * @param character The WhiteMage character attempting to equip the wand.
   * @return `true` as WhiteMages can equip wands.
   */
  def canBeEquipped(character: WhiteMage): Boolean = true

  /**
   * Determines if the wand can be equipped by a BlackMage.
   *
   * @param character The BlackMage character attempting to equip the wand.
   * @return `true` as BlackMages can equip wands.
   */
  def canBeEquipped(character: BlackMage): Boolean = true

}

