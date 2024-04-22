package entity

import weapon.Weapon

trait Entity {
  val _name: String
  val _hit_points: Int
  val _defense: Int
  val _weight: Int

  var _current_hit_points: Int
  var _state: Boolean

  val _isPlayer: Boolean


  def name: String
  def hit_points: Int
  def defense: Int
  def weight: Int
  def current_hit_points: Int
  def current_hit_points_=(new_current_hit_points: Int): Unit
  def state: Boolean
  def state_=(new_character_state: Boolean): Unit
  def barValue: Int



}
