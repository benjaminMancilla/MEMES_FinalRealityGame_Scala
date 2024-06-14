package entity.character

import entity.Entity
import weapon.Weapon

/**
 * Trait representing a character entityE in a game.
 * A character is an entityE with additional properties such as an equipped weaponE.
 */
trait Character extends Entity with PICharacter {

  /**
   * Changes the equipped weaponE of the character.
   *
   * @param new_weapon The new weaponE to be equipped.
   */
  def changeWeapon(new_weapon: Option[Weapon]): Unit

  /**
   * Unequips the currently equipped weaponE of the character.
   */
  def unequipWeapon(): Unit

  /**
   * Gets the currently equipped weaponE of the character.
   *
   * @return The currently equipped weaponE.
   */
  def equipped_weapon: Option[Weapon]

  /**
   * Determines if certain weaponE can be equipped by a the Character.
   *
   * @param newWeapon The new weaponE that is going to be equipped.
   * @return `true` if the character can equip the weaponE.
   */
  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean

}



