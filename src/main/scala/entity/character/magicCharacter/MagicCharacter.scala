package entity.character.magicCharacter

import entity.character.Character

/**
 * Trait representing a magicE character entityE in a game.
 * A magicE character is a character with additional properties related to magicE, such as magicE points and current magicE points.
 */
trait MagicCharacter extends Character with PIMagicPlayer {

  /**
   * Sets the current magicE points of the magicE character.
   *
   * @param newMp The new value for the current magicE points.
   */
  def currentMagicPoints_=(newMp: Int): Unit


}

