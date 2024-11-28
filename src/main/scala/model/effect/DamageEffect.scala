package model.effect

/**
 * Trait representing a damage effect that can be applied to an entity in a game.
 *
 * Damage effects are conditions that primarily deal damage to an entity, such as
 * magical damage over time, direct damage, or other harmful impacts. This trait extends
 * the basic `Effect` trait to include properties and behaviors specific to damaging effects.
 */
trait DamageEffect extends Effect {

  /**
   * Gets the amount of magical damage dealt by the effect.
   *
   * This method returns the amount of magical damage that the effect will inflict
   * on the target entity.
   *
   * @return The amount of magical damage.
   */
  def magicDamage: Int

}