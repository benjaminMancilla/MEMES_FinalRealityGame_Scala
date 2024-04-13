package enemy

import enemy.Enemy



class ConcreteEnemy (val _name: String,
                     val _hit_points: Int,
                     val _defense: Int,
                     val _weight: Int,
                     val _attack: Int)
  extends Enemy {
  var _enemyState: Boolean = true
  var _current_hit_points: Int = hitPoints


  def name: String = _name
  def hitPoints: Int = _hit_points
  def defense: Int = _defense
  def weight: Int = _weight
  def attack: Int = _attack
  def currentHitPoints: Int = _current_hit_points
  def currentHitPoints_=(newCurrentHitPoints: Int): Unit = {
    _current_hit_points = newCurrentHitPoints
  }

  def enemyState: Boolean = _enemyState
  def enemyState_=(newEnemyState: Boolean): Unit = {
    _enemyState = newEnemyState
  }

}
