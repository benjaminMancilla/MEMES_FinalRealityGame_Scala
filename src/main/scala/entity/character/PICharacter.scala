package entity.character

import entity.PIEntity
import weapon.Weapon

/**
 * Trait representing a character entity in a game.
 * A character is an entity with additional properties such as an equipped weapon and a type name.
 */
trait PICharacter extends PIEntity {

  /**
   * The weapon equipped by the character.
   */
  def equipped_weapon: Weapon

  /**
   * The type name of the character.
   */
  def typeName: String

}

