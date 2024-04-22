package entity

import weapon.Weapon

abstract class AbstractEntity extends Entity {

  val _name: String
  val _hit_points: Int
  val _defense: Int
  val _weight: Int
  val _isPlayer: Boolean


  var _current_hit_points: Int = _hit_points
  var _state: Boolean = true


  def name: String = _name
  def hit_points: Int = _hit_points
  def defense: Int = _defense
  def weight: Int = _weight
  def current_hit_points: Int = _current_hit_points
  def current_hit_points_=(new_current_hit_points: Int): Unit = {
    _current_hit_points = new_current_hit_points
  }

  def state: Boolean = _state

  def state_=(new_character_state: Boolean): Unit = {
    _state = new_character_state
  }



}
