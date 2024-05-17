package entity.enemy

import entity.AbstractEntity
import exceptions.{InvalidStatException, Require}

/**
 * Concrete class representing an enemy entity in a game.
 * An enemy is a character that can attack.
 *
 * @param nameI       The name of the enemy.
 * @param hit_pointsI The initial hit points of the enemy.
 * @param defenseI    The defense level of the enemy.
 * @param weightI     The weight of the enemy.
 * @param attackI     The attack strength of the enemy.
 */
class ConcreteEnemy(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, attackI: Int)
  extends AbstractEntity(nameI, hit_pointsI, defenseI, weightI) with Enemy {

  /**
   * Attack strength of the enemy.
   */
  private val _attack: Int = try {Require.Stat(attackI, "attack") in (0 to 10000)}
  catch {
    case _: InvalidStatException => 10
  }

  /**
   * Indicates whether the enemy is controlled by the player.
   */
  override val _isPlayer = false

  /**
   * Gets the attack strength of the enemy.
   *
   * @return The attack strength of the enemy.
   */
  def attack: Int = _attack

  /**
   * Value for the enemy's status bar (e.g., representing visually the weight).
   *
   * @return The value for the enemy's status bar.
   */
  def barValue: Int = {
    weight
  }
}

