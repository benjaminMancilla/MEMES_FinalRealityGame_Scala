package model.effect

import exceptions.Require
import model.entity.Entity

class Poisoned(turnsEffectI: Int = 4, magicDamageI: Int) extends AbstractDamageEffect(turnsEffectI, magicDamageI) with DamageEffect {
  override protected val _effectName: String = "Poison effect"
  def applyEffect(target: Entity): Unit = {
    target.receiveDamage(magicDamage / 3)
    passTurn()
  }

}