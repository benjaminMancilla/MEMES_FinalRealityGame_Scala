package entity.enemy

import entity.AbstractEntity

class ConcreteEnemy (name: String,
                     hit_points: Int,
                     defense: Int,
                     weight: Int,
                     attack: Int)
  extends AbstractEntity(name, hit_points, defense, weight) with Enemy {
  private val _attack: Int = attack
  override val _isPlayer = false
  def attack: Int = _attack
  def barValue: Int = {
    weight
  }



}
