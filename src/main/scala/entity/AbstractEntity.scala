package entity

import weapon.Weapon

abstract class AbstractEntity extends Entity with PIEntity {

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
  protected def current_hit_points_=(new_current_hit_points: Int): Unit = {
    _current_hit_points = new_current_hit_points
  }
  def state: Boolean = _state
  protected def state_=(new_character_state: Boolean): Unit = {
    _state = new_character_state
  }

  protected def doDamage(entity: Entity, damage: Int): Unit = {
    if (!state) {
      printf(s"$name no puede atacar porque ya esta fuera de combate.")
    }
    else {
      printf(s"$name atacara a ${entity.name}")
      val extraDmg = entity.receiveDamage(damage)
      if (extraDmg > 0) {
        printf(s"$name ha derrotado a ${entity.name} con un bonus de $extraDmg de DMG!!!!!.")
      }
      else if (extraDmg == 0) {
        printf(s"$name ha derrotado a ${entity.name} justo!!")
      }
      else {
        printf(s"$name ha atacado a ${entity.name}")
      }
    }
  }

  protected def receiveDamage(damage: Int): Int = {
    val totalDmg = damage - defense
    var extraDmg = -1 // si queda negativo entonces no se derroto al character
    if (current_hit_points - totalDmg <= 0) {
      extraDmg = totalDmg - current_hit_points
      current_hit_points = 0
      state = false
    }
    else {
      printf(s"$name ha recibido $totalDmg de DMG")
      current_hit_points -= totalDmg
      state = true
    }
    extraDmg
  }

  def doAttack(entity: Entity, damage: Int): Unit = {
    doDamage(entity: Entity, damage: Int)
  }




}
