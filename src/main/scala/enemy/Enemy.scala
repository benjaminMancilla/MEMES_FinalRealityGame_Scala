package enemy

import weapon.Weapon

trait Enemy {
  val _name: String
  val _hit_points: Int
  val _defense: Int
  val _attack: Int
  val _weight: Int

  var _current_hit_points: Int
  var _enemyState: Boolean



  def name: String
  def hitPoints: Int
  def defense: Int
  def weight: Int
  def attack: Int
  def currentHitPoints: Int
  def currentHitPoints_=(newCurrentHitPoints: Int): Unit
  def enemyState: Boolean
  def enemyState_=(newCharacterState: Boolean): Unit
}
