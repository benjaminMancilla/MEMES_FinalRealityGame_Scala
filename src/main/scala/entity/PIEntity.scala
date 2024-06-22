package entity

import effect.Effect

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

  def activeEffects: ListBuffer[Effect]

  def skipTurn: Boolean

}

