package effect

import entity.Entity

trait Effect {
  def passTurn() : Unit
  def applyEffect(target: Entity): Unit

}
