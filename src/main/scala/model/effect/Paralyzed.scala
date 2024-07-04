package model.effect

import model.entity.Entity

class Paralyzed(turnsEffectI: Int = 1) extends AbstractEffect(turnsEffectI) with StateEffect {
  override protected val _effectName: String = "Paralyze effect"
  def applyEffect(target: Entity): Unit = {
    target.skipTurn = true
    passTurn()
  }

}
