package entity

import weapon.Weapon

trait Entity extends PIEntity {
  protected val _name: String
  protected val _hit_points: Int
  protected val _defense: Int
  protected val _weight: Int

  protected var _current_hit_points: Int
  protected var _state: Boolean

  protected val _isPlayer: Boolean
  def current_hit_points_=(new_current_hit_points: Int): Unit
  def state_=(new_character_state: Boolean): Unit
  def doDamage(entity: Entity, damage: Int): Unit
  def receiveDamage(damage: Int): Int
  def doAttack(entity: Entity, damage: Int): Unit



}
