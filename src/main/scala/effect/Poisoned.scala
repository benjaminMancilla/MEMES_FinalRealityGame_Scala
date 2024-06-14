package effect
import entity.Entity

class Poisoned(turnsEffectI: Int = 4, magicDamage: Int) extends AbstractEffect(turnsEffectI) {
  def applyEffect(target: Entity): Unit = {
    target.receiveDamage(magicDamage / 3)
    passTurn()
  }

}
