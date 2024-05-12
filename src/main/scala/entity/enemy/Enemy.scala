package entity.enemy

import entity.Entity

/**
 * Trait representing an enemy entity in a game.
 * An enemy is a character that can attack.
 */
trait Enemy extends Entity {

  /**
   * Attack strength of the enemy.
   */
  def attack: Int

}
