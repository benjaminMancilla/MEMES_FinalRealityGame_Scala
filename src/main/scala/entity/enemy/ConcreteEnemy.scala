package entity.enemy

import entity.AbstractEntity

class ConcreteEnemy (nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, attackI: Int)
  extends AbstractEntity(nameI, hit_pointsI, defenseI, weightI) with Enemy {

  private val _attack: Int = try {
    if (attackI>=0) attackI else throw new IllegalArgumentException("Attack must be larger than -1")
  } catch {
    case _: IllegalArgumentException => 0
  }

  override val _isPlayer = false
  def attack: Int = _attack
  def barValue: Int = {
    weight
  }



}
