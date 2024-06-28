package entity

import controller.visitor.ActionVisitor
import effect.Effect
import magic.{DefensiveSpell, OffensiveSpell}

import scala.collection.mutable.ListBuffer


/**
 * Trait that extends PIEntity and defines additional behavior for an entityE in a game.
 * An entityE can be a character controlled by the player or an enemy.
 */
trait Entity extends PIEntity {

  /**
   * Sets the current hit points of the entityE.
   * @param new_current_hit_points The new value for the current hit points.
   */
  def current_hit_points_=(new_current_hit_points: Int): Unit

  /**
   * Sets the state of the entityE.
   * @param new_character_state The new state of the entityE (true for alive, false for dead).
   */
  def state_=(new_character_state: Boolean): Unit

  /**
   * Inflicts damage to another entityE during an attack.
   * @param entity The entityE receiving the damage.
   * @param damage The amount of damage to be inflicted.
   */
  def doDamage(entity: Entity, damage: Int): Unit

  /**
   * Receives damage from an attack.
   * @param damage The amount of damage received.
   * @return The remaining hit points after receiving the damage.
   */
  def receiveDamage(damage: Int): Int

  /**
   * Performs an attack on another entityE.
   * @param entity The entityE being attacked.
   * @param damage The amount of damage to be inflicted.
   */
  def doAttack(entity: Entity, damage: Int): Unit

  /**
   * Performs healing on another entityE.
   * @param entity The entityE being healed.
   * @param heal The amount of healing to be performed.
   */
  def doHealing(entity: Entity, heal: Int): Unit

  /**
   * Receives healing from a spell or ability.
   * @param heal The amount of healing received.
   * @return The remaining hit points after receiving the healing.
   */
  def receiveHealing(heal: Int): Int

  /**
   * Performs healing on the entityE itself.
   * @param entity The entityE performing the healing.
   * @param heal The amount of healing to be performed.
   */
  def doHeal(entity: Entity, heal: Int): Unit

  /**
   * Checks if the target entityE is valid for an offensive spell.
   * @param spell The offensive spell being cast.
   */
  def checkSpell(spell: OffensiveSpell): Unit

  /**
   * Checks if the target entityE is valid for a defensive spell.
   * @param spell The defensive spell being cast.
   */
  def checkSpell(spell: DefensiveSpell): Unit

  /**
   * Add effect to current active effects.
   *
   * @param effect New added effect.
   */
  def addEffect(effect: Effect): Unit

  def skipTurn_=(bool: Boolean): Unit

  def accept(visitor: ActionVisitor): Unit

}


