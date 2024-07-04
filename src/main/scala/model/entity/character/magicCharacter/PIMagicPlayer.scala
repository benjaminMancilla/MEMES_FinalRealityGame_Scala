package model.entity.character.magicCharacter

import model.entity.character.PICharacter

/**
 * Trait representing a magicE player character entityE in a game.
 * A magicE player is a character with additional properties related to magicE, such as magicE points and current magicE points.
 */
trait PIMagicPlayer extends PICharacter {

  /**
   * The total magicE points of the magicE player.
   */
  def magicPoints: Int

  /**
   * The current magicE points of the magicE player.
   */
  def currentMagicPoints: Int

}

