package effect
import entity.Entity
import exceptions.Require

class Burned(turnsEffectI: Int = 3, magicDamageI: Int) extends AbstractDamageEffect(turnsEffectI, magicDamageI) with DamageEffect {
  override protected val _effectName: String = "Burn effect"
  def applyEffect(target: Entity): Unit = {
    target.receiveDamage(magicDamage / 2)
    passTurn()
  }
}
