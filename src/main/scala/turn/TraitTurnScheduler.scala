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
   * Resets the action bar value for a specific entityE to its initial state.
   *
   * @param entity The entityE whose action bar value needs to be reset.
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
   * Adds a new entityE to the turn scheduler.
   *
   * @param new_char The new entityE to be added.
   */
  def addEntity(new_char: Entity): Unit

  /**
   * Removes an entityE from the turn scheduler.
   *
   * @param old_char The entityE to be removed.
   */
  def removeEntity(old_char: Entity): Unit

  /**
   * Dequeues the next entityE that is ready to perform an action.
   *
   * @return The next entityE ready to perform an action.
   */
  def dequeueReady(): Entity

  def updateEntity(entity: Entity): Unit

}

