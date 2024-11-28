package model.turn

import model.entity.Entity
import scala.collection.mutable.ArrayBuffer

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

  /**
   * Updates the information of a specific entity.
   *
   * This method is used to refresh or modify the data related to the given entity, such as its action bar value
   * or other turn-related attributes.
   *
   * @param entity The entity whose information needs to be updated.
   */
  def updateEntity(entity: Entity): Unit

  /**
   * ArrayBuffer holding information about the entities' turn status.
   *
   * Each tuple contains an entity, its current action bar value, its maximum action bar value, and a boolean
   * indicating whether the entity is waiting for its turn.
   */
  val _turn_info: ArrayBuffer[(Entity, Int, Int, Boolean)]

}


