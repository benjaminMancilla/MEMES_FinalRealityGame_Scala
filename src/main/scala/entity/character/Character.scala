package entity.character

import entity.Entity
import weapon.Weapon

/**
 * Trait representing a character entity in a game.
 * A character is an entity with additional properties such as an equipped weapon.
 */
trait Character extends Entity with PICharacter {

  /**
   * Changes the equipped weapon of the character.
   *
   * @param new_weapon The new weapon to be equipped.
   */
  def changeWeapon(new_weapon: Option[Weapon]): Unit

  /**
   * Unequips the currently equipped weapon of the character.
   */
  def unequipWeapon(): Unit

  /**
   * Gets the currently equipped weapon of the character.
   *
   * @return The currently equipped weapon.
   */
  def equipped_weapon: Option[Weapon]

  /**
   * Determines if certain weapon can be equipped by a the Character.
   *
   * @param newWeapon The new weapon that is going to be equipped.
   * @return `true` if the character can equip the weapon.
   */
  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean

}



