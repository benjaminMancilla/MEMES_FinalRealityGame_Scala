package effect
import entity.Entity

class Burned(turnsEffectI: Int = 3, magicDamage: Int) extends AbstractEffect(turnsEffectI) {
  def applyEffect(target: Entity): Unit = {
    target.receiveDamage(magicDamage / 2)
  }
}
