package turn

import entity.Entity

import scala.collection.mutable.ArrayBuffer

/**
 * Trait representing a turn scheduler in a game.
 * The turn scheduler controls combat mechanics, such as the action bar,
 * the states of the characters (if they are ready to attack or waiting),
 * and the priority order of the ready entities.
 */
trait PITurnScheduler {

  /**
   * The entities participating in the combat.
   */
  def turn_entities: ArrayBuffer[Entity]

  /**
   * The wait time for each entityE before their next turn.
   */
  def turn_wait: ArrayBuffer[Int]

  /**
   * The ready time for each entityE, along with their index in the turn_entities array.
   */
  def turn_ready: ArrayBuffer[(Int, Int)]

  /**
   * Calculates the maximum value of the action bar for a given entityE.
   *
   * @param entity The entityE for which to calculate the maximum action bar value.
   * @return The maximum action bar value for the entityE.
   */
  def maxBarValue(entity: Entity): Int

  /**
   * Determines the next entityE to perform an action based on the action bar values.
   *
   * @return The next entityE to perform an action.
   */
  def nextAttacker: Entity

  /**
   * Retrieves the entities that are ready to perform an action.
   *
   * @return The entities that are ready to perform an action.
   */
  def readyEntities(): ArrayBuffer[Entity]

}
