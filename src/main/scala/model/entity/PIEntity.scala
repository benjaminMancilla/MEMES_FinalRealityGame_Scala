package model.entity

import model.effect.Effect
import scala.collection.mutable.ListBuffer

/**
 * Trait that defines the basic properties of an entityE in a game.
 * An entityE can be a character controlled by the player or an enemy.
 */
trait PIEntity {

  /**
   * Name of the entityE.
   */
  def name: String

  /**
   * Maximum hit points of the entityE.
   */
  def hit_points: Int

  /**
   * Defense level of the entityE.
   */
  def defense: Int

  /**
   * Weight of the entityE.
   */
  def weight: Int

  /**
   * Current hit points of the entityE.
   */
  def current_hit_points: Int

  /**
   * Current state of the entityE (alive or dead).
   */
  def state: Boolean

  /**
   * Value for the entityE's combat bar.
   */
  def barValue: Int

  /**
   * Indicates whether the entityE is controlled by the player or not.
   * @return true if the entityE is controlled by the player, false otherwise.
   */
  def isPlayer: Boolean

  /**
   * List of active effects on the entity.
   *
   * This method returns a mutable list of effects that are currently affecting
   * the entity. Effects can include status conditions such as poison, paralysis,
   * buffs, debuffs, or any other temporary changes to the entity's stats or abilities.
   *
   * @return A ListBuffer of Effect objects representing the active effects on the entity.
   */
  def activeEffects: ListBuffer[Effect]


  /**
   * Indicates whether the entity should skip its turn.
   *
   * This method determines if the entity is unable to take an action during its
   * turn, typically due to being affected by a status condition such as paralysis,
   * stun, or sleep. If this method returns true, the entity will be skipped in the
   * turn order.
   *
   * @return true if the entity should skip its turn, false otherwise.
   */
  def skipTurn: Boolean

}

