package entity.enemy

import entity.Entity

trait Enemy extends Entity{
  val _attack: Int
  def attack: Int

}
