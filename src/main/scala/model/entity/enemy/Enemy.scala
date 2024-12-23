package model.entity.enemy

import model.entity.Entity

/**
 * Trait representing an enemy entityE in a game.
 * An enemy is a character that can attack.
 */
trait Enemy extends Entity {

  /**
   * Attack strength of the enemy.
   */
  def attack: Int


}
