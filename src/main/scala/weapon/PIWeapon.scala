package weapon

import entity.character.{BlackMage, Character, Ninja, Paladin, Warrior, WhiteMage}

/**
 * Trait representing a weapon in the game.
 * Weapons are equipment that characters can use.
 * The compatibility of a weapon with a character type determines whether it can be equipped by that character.
 * Weapons can either be ordinary or magical.
 * Magical weapons can be used to cast spells.
 * Each weapon can only be equipped by one character at a time.
 * No value for equipped weapon represents being unarmed.
 */
trait PIWeapon {

  /** The name of the weapon. */
  def name: String

  /** The attack points of the weapon. */
  def attackPoints: Int

  /** The weight of the weapon. */
  def weight: Int

  /** The owner of the weapon, if any. */
  def owner: Option[Character]

  /**
   * Checks if the weapon can be equipped by a warrior character.
   *
   * @param character The warrior character.
   * @return True if the weapon can be equipped by the warrior, false otherwise.
   */
  def canBeEquipped(character: Warrior): Boolean

  /**
   * Checks if the weapon can be equipped by a paladin character.
   *
   * @param character The paladin character.
   * @return True if the weapon can be equipped by the paladin, false otherwise.
   */
  def canBeEquipped(character: Paladin): Boolean

  /**
   * Checks if the weapon can be equipped by a ninja character.
   *
   * @param character The ninja character.
   * @return True if the weapon can be equipped by the ninja, false otherwise.
   */
  def canBeEquipped(character: Ninja): Boolean

  /**
   * Checks if the weapon can be equipped by a white mage character.
   *
   * @param character The white mage character.
   * @return True if the weapon can be equipped by the white mage, false otherwise.
   */
  def canBeEquipped(character: WhiteMage): Boolean

  /**
   * Checks if the weapon can be equipped by a black mage character.
   *
   * @param character The black mage character.
   * @return True if the weapon can be equipped by the black mage, false otherwise.
   */
  def canBeEquipped(character: BlackMage): Boolean

}

