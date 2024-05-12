package turn

import entity.Entity

/**
 * Trait representing a turn scheduler with additional functionality for managing turn-based combat mechanics in a game.
 * This trait extends the PITurnScheduler trait.
 */
trait TraitTurnScheduler extends PITurnScheduler {

  /**
   * Updates the maximum action bar values for all entities.
   */
  def updateMaxBars(): Unit

  /**
   * Resets the action bar values for all entities to their initial state.
   */
  def resetAllBarValues(): Unit

  /**
   * Resets the action bar value for a specific entity to its initial state.
   *
   * @param entity The entity whose action bar value needs to be reset.
   */
  def resetBarValue(entity: Entity): Unit

  /**
   * Updates the action progress of all entities by advancing their action bar values.
   *
   * @param k The value by which to advance the action bar values.
   */
  def updateActionProgress(k: Int): Unit

  /**
   * Checks for entities that are in a waiting state and updates their wait times.
   */
  def checkWaitEntities(): Unit

  /**
   * Adds a new entity to the turn scheduler.
   *
   * @param new_char The new entity to be added.
   */
  def addEntity(new_char: Entity): Unit

  /**
   * Removes an entity from the turn scheduler.
   *
   * @param old_char The entity to be removed.
   */
  def removeEntity(old_char: Entity): Unit

  /**
   * Dequeues the next entity that is ready to perform an action.
   *
   * @return The next entity ready to perform an action.
   */
  def dequeueReady(): Entity

}

