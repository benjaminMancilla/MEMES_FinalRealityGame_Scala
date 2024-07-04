package model.entity.character

import model.entity.PIEntity
import model.weapon.Weapon

/**
 * Trait representing a character entityE in a game.
 * A character is an entityE with additional properties such as an equipped weaponE and a type name.
 */
trait PICharacter extends PIEntity {

  /**
   * The weaponE equipped by the character.
   */
  def equipped_weapon: Option[Weapon]

  /**
   * The type name of the character.
   */
  def typeName: String

}

