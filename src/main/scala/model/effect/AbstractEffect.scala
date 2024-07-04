package model.effect

import exceptions.Require
import exceptions.effectE.InvalidTurnAmount
import model.entity.Entity

abstract class AbstractEffect(turnsEffectI: Int) extends Effect {
  protected val _effectName: String = "Generic effect"
  private var _turnEffect: Int = {Require.Stat(turnsEffectI, "turnEffect") atLeast 1}
  def passTurn(): Unit = {
    if (turnEffect>0){
      turnEffect = turnEffect-1
    }
    else {
      throw new InvalidTurnAmount(s"${this.effectName} has an invalid amount of remaining turns equal to ${this.turnEffect}")
    }
  }

  def applyEffect(target: Entity): Unit

  def turnEffect: Int = _turnEffect
  def turnEffect_=(newTurnEffect: Int): Unit = {
    _turnEffect = newTurnEffect
  }

  def effectName : String = _effectName

}
