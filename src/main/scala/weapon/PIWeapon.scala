package weapon

import entity.character.commonCharacter.{Ninja, Paladin, Warrior}
import entity.character.magicCharacter.{BlackMage, WhiteMage}
import entity.character.Character

/**
 * Trait representing a weaponE in the game.
 * Weapons are equipment that characters can use.
 * The compatibility of a weaponE with a character type determines whether it can be equipped by that character.
 * Weapons can either be ordinary or magical.
 * Magical weapons can be used to cast spells.
 * Each weaponE can only be equipped by one character at a time.
 * No value for equipped weaponE represents being unarmed.
 */
trait PIWeapon {

  /** The name of the weaponE. */
  def name: String

  /** The attack points of the weaponE. */
  def attackPoints: Int

  /** The weight of the weaponE. */
  def weight: Int

  /** The owner of the weaponE, if any. */
  def owner: Option[Character]

  /**
   * Checks if the weaponE can be equipped by a warrior character.
   *
   * @param character The warrior character.
   * @return True if the weaponE can be equipped by the warrior, false otherwise.
   */
  def canBeEquipped(character: Warrior): Boolean

  /**
   * Checks if the weaponE can be equipped by a paladin character.
   *
   * @param character The paladin character.
   * @return True if the weaponE can be equipped by the paladin, false otherwise.
   */
  def canBeEquipped(character: Paladin): Boolean

  /**
   * Checks if the weaponE can be equipped by a ninja character.
   *
   * @param character The ninja character.
   * @return True if the weaponE can be equipped by the ninja, false otherwise.
   */
  def canBeEquipped(character: Ninja): Boolean

  /**
   * Checks if the weaponE can be equipped by a white mage character.
   *
   * @param character The white mage character.
   * @return True if the weaponE can be equipped by the white mage, false otherwise.
   */
  def canBeEquipped(character: WhiteMage): Boolean

  /**
   * Checks if the weaponE can be equipped by a black mage character.
   *
   * @param character The black mage character.
   * @return True if the weaponE can be equipped by the black mage, false otherwise.
   */
  def canBeEquipped(character: BlackMage): Boolean

}

