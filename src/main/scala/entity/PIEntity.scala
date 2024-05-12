package entity

/**
 * Trait that defines the basic properties of an entity in a game.
 * An entity can be a character controlled by the player or an enemy.
 */
trait PIEntity {

  /**
   * Name of the entity.
   */
  def name: String

  /**
   * Maximum hit points of the entity.
   */
  def hit_points: Int

  /**
   * Defense level of the entity.
   */
  def defense: Int

  /**
   * Weight of the entity.
   */
  def weight: Int

  /**
   * Current hit points of the entity.
   */
  def current_hit_points: Int

  /**
   * Current state of the entity (alive or dead).
   */
  def state: Boolean

  /**
   * Value for the entity's status bar (e.g., representing visually the hit points).
   */
  def barValue: Int

  /**
   * Indicates whether the entity is controlled by the player or not.
   * @return true if the entity is controlled by the player, false otherwise.
   */
  def isPlayer: Boolean

}

