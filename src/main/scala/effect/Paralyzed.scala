package effect

import entity.Entity

class Paralyzed(turnsEffectI: Int = 1) extends AbstractEffect(turnsEffectI) {
  def applyEffect(target: Entity): Unit = {
    // No action needed since paralysis simply skips the turn
    passTurn()
  }

}
