package model.effect

import model.entity.Entity

/**
 * Class representing a paralyzed effect that can be applied to an entity in a game.
 *
 * The paralyzed effect disables the target entity for a number of turns, preventing
 * it from performing any actions. This effect is represented by the `Paralyzed` class,
 * which extends `AbstractEffect` and implements the `StateEffect` trait.
 *
 * @param turnsEffectI The number of turns the effect lasts. Defaults to 1 turn.
 */
class Paralyzed(turnsEffectI: Int = 1)
  extends AbstractEffect(turnsEffectI) with StateEffect {

  /**
   * The name of the effect.
   *
   * This value is used to identify the effect.
   */
  override protected val _effectName: String = "Paralyze effect"

  /**
   * Applies the paralyze effect to the target entity.
   *
   * This method sets the `skipTurn` flag to true for the target entity,
   * preventing it from acting on its next turn, and then progresses the
   * effect by one turn.
   *
   * @param target The entity to which the effect is applied.
   */
  def applyEffect(target: Entity): Unit = {
    target.skipTurn = true
    passTurn()
  }

}

