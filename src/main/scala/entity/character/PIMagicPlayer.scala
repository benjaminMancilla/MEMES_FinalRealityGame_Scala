package entity.character

/**
 * Trait representing a magic player character entity in a game.
 * A magic player is a character with additional properties related to magic, such as magic points and current magic points.
 */
trait PIMagicPlayer extends PICharacter {

  /**
   * The total magic points of the magic player.
   */
  def magicPoints: Int

  /**
   * The current magic points of the magic player.
   */
  def currentMagicPoints: Int

}

