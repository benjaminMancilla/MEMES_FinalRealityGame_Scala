package entity.enemy

import entity.AbstractEntity

class ConcreteEnemy (val _name: String,
                     val _hit_points: Int,
                     val _defense: Int,
                     val _weight: Int,
                     val _attack: Int,
                     val _isPlayer: Boolean = false)
  extends AbstractEntity with Enemy {
  def attack: Int = _attack
  def barValue: Int = {
    weight
  }



}
