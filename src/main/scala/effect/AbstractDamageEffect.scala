package effect

import exceptions.Require

abstract class AbstractDamageEffect(turnsEffectI: Int, magicDamageI: Int) extends AbstractEffect(turnsEffectI: Int) with DamageEffect {
  private val _magicDamage = {Require.Stat(magicDamageI, "magicDamage") atLeast 0}
  def magicDamage: Int = _magicDamage

}
