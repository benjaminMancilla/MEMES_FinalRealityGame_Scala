package entity.character.magicCharacter

import entity.character.Character

/**
 * Trait representing a magic character entity in a game.
 * A magic character is a character with additional properties related to magic, such as magic points and current magic points.
 */
trait MagicCharacter extends Character with PIMagicPlayer {

  /**
   * Sets the current magic points of the magic character.
   *
   * @param newMp The new value for the current magic points.
   */
  def currentMagicPoints_=(newMp: Int): Unit


}

