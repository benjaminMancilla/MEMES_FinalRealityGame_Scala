package entity


/**
 * Trait that extends PIEntity and defines additional behavior for an entity in a game.
 * An entity can be a character controlled by the player or an enemy.
 */
trait Entity extends PIEntity {

  /**
   * Sets the current hit points of the entity.
   * @param new_current_hit_points The new value for the current hit points.
   */
  def current_hit_points_=(new_current_hit_points: Int): Unit

  /**
   * Sets the state of the entity.
   * @param new_character_state The new state of the entity (true for alive, false for dead).
   */
  def state_=(new_character_state: Boolean): Unit

  /**
   * Inflicts damage to another entity during an attack.
   * @param entity The entity receiving the damage.
   * @param damage The amount of damage to be inflicted.
   */
  def doDamage(entity: Entity, damage: Int): Unit

  /**
   * Receives damage from an attack.
   * @param damage The amount of damage received.
   * @return The remaining hit points after receiving the damage.
   */
  def receiveDamage(damage: Int): Int

  /**
   * Performs an attack on another entity.
   * @param entity The entity being attacked.
   * @param damage The amount of damage to be inflicted.
   */
  def doAttack(entity: Entity, damage: Int): Unit

}

