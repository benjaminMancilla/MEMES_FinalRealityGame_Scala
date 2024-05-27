package effect

import entity.Entity
import exceptions.Require

abstract class AbstractEffect(turnsEffectI: Int) extends Effect {
  private var _turnEffect: Int = {Require.Stat(turnsEffectI, "turnEffect") atLeast 1}
  def passTurn(): Unit = {
    turnEffect = turnEffect-1
  }

  def applyEffect(target: Entity): Unit

  private def turnEffect: Int = _turnEffect
  private def turnEffect_=(newTurnEffect: Int): Unit = {
    _turnEffect = newTurnEffect
  }

}
