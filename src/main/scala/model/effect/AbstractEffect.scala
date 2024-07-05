package model.effect

import exceptions.Require
import exceptions.effectE.InvalidTurnAmount
import model.entity.Entity

/**
 * Abstract class representing a generic effect that can be applied to an entity in a game.
 *
 * This class provides the basic implementation for an effect, including properties
 * for the effect's name and duration, and methods for progressing the effect by one
 * turn and applying the effect to a target entity. Specific types of effects should
 * extend this class to provide their own behavior.
 *
 * @param turnsEffectI The number of turns the effect lasts.
 */
abstract class AbstractEffect(turnsEffectI: Int) extends Effect {

  /**
   * The name of the effect.
   *
   * This value is used to identify the effect. Subclasses can override this value
   * to provide specific effect names.
   */
  protected val _effectName: String = "Generic effect"

  /**
   * The number of turns the effect will last.
   *
   * This value must be at least 1. If an invalid value is provided, an exception will be thrown.
   */
  private var _turnEffect: Int = {Require.Stat(turnsEffectI, "turnEffect") atLeast 1}

  /**
   * Progresses the effect by one turn.
   *
   * This method decrements the remaining duration of the effect by one turn. If the
   * remaining duration is already zero or less, an exception is thrown.
   *
   * @throws InvalidTurnAmount if the effect has an invalid amount of remaining turns.
   */
  def passTurn(): Unit = {
    if (turnEffect > 0) {
      turnEffect = turnEffect - 1
    } else {
      throw new InvalidTurnAmount(s"${this.effectName} has an invalid amount of remaining turns equal to ${this.turnEffect}")
    }
  }

  /**
   * Applies the effect to a target entity.
   *
   * This method must be implemented by subclasses to define the specific behavior
   * of the effect when applied to a target entity.
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
  def turnEffect: Int = _turnEffect

  /**
   * Sets the remaining duration of the effect.
   *
   * This method updates the number of turns the effect will continue to be active.
   *
   * @param newTurnEffect The new duration in turns for the effect.
   */
  def turnEffect_=(newTurnEffect: Int): Unit = {
    _turnEffect = newTurnEffect
  }

  /**
   * Gets the name of the effect.
   *
   * This method returns a string representing the name of the effect,
   * which can be used for identification or logging purposes.
   *
   * @return The name of the effect.
   */
  def effectName: String = _effectName

}

