package model.effect

import model.entity.Entity

/**
 * Class representing a poisoned effect that can be applied to an entity in a game.
 *
 * The poisoned effect deals magical damage to the target entity over several turns.
 * This effect is represented by the `Poisoned` class, which extends `AbstractDamageEffect`
 * and implements the `DamageEffect` trait.
 *
 * @param turnsEffectI The number of turns the effect lasts.
 * @param magicDamageI The amount of magical damage dealt by the effect.
 */
class Poisoned(turnsEffectI: Int = 4, magicDamageI: Int)
  extends AbstractDamageEffect(turnsEffectI, magicDamageI) with DamageEffect {

  /**
   * The name of the effect.
   *
   * This value is used to identify the effect.
   */
  override protected val _effectName: String = "Poison effect"

  /**
   * Applies the poison effect to the target entity.
   *
   * This method inflicts magical damage on the target entity and then progresses
   * the effect by one turn.
   *
   * @param target The entity to which the effect is applied.
   */
  def applyEffect(target: Entity): Unit = {
    target.receiveDamage(magicDamage / 3)
    passTurn()
  }

}
