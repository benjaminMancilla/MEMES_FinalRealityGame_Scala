package model.effect

import model.entity.Entity

/**
 * Trait representing an effect that can be applied to an entity in a game.
 *
 * Effects are temporary modifications to an entity's state or behavior, such as
 * status conditions (e.g., poison, stun) or temporary buffs/debuffs. This trait
 * defines the basic operations that all effects must implement.
 */
trait Effect {

  /**
   * Applies the effect for the current turn.
   *
   * This method is called to progress the effect by one turn. It is typically used
   * to decrement the duration of the effect or to apply any ongoing effects.
   */
  def passTurn(): Unit

  /**
   * Applies the effect to a target entity.
   *
   * This method is used to apply the effect's impact on the target entity,
   * modifying its state or properties based on the effect's nature.
   *
   * @param target The entity to which the effect is applied.
   */
  def applyEffect(target: Entity): Unit

  /**
   * Gets the remaining duration of the effect.
   *
   * This method returns the number of turns the effect will continue to be active.
   *
   * @return The number of turns the effect will last.
   */
  def turnEffect: Int

  /**
   * Sets the remaining duration of the effect.
   *
   * This method updates the number of turns the effect will continue to be active.
   *
   * @param newTurnEffect The new duration in turns for the effect.
   */
  def turnEffect_=(newTurnEffect: Int): Unit

  /**
   * Gets the name of the effect.
   *
   * This method returns a string representing the name of the effect,
   * which can be used for identification or logging purposes.
   *
   * @return The name of the effect.
   */
  def effectName: String

}
