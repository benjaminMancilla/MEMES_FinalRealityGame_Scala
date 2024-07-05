package model.effect

import exceptions.Require

/**
 * Abstract class representing a generic damage effect that can be applied to an entity in a game.
 *
 * This class extends `AbstractEffect` and implements the `DamageEffect` trait to provide
 * a basic implementation for damage effects, including properties for the effect's duration
 * and the amount of magical damage it deals. Specific types of damage effects should
 * extend this class to provide their own behavior.
 *
 * @param turnsEffectI The number of turns the effect lasts.
 * @param magicDamageI The amount of magical damage dealt by the effect.
 */
abstract class AbstractDamageEffect(turnsEffectI: Int, magicDamageI: Int)
  extends AbstractEffect(turnsEffectI: Int) with DamageEffect {

  /**
   * The amount of magical damage dealt by the effect.
   *
   * This value must be at least 0. If an invalid value is provided, an exception will be thrown.
   */
  private val _magicDamage = {Require.Stat(magicDamageI, "magicDamage") atLeast 0}

  /**
   * Gets the amount of magical damage dealt by the effect.
   *
   * This method returns the amount of magical damage that the effect will inflict
   * on the target entity.
   *
   * @return The amount of magical damage.
   */
  def magicDamage: Int = _magicDamage

}
