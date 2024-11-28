package model.entity

import controller.visitor.ActionVisitor
import model.effect.Effect
import model.entity.character.Character
import model.entity.enemy.ConcreteEnemy
import model.magic.{DefensiveSpell, Magic, OffensiveSpell}
import model.weapon.Weapon

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
   */
  def doGenericAttack(entity: Entity): Unit

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

  /**
   * Sets whether the entity should skip its turn.
   *
   * This method allows you to update the skip turn status of the entity.
   * When set to true, the entity will be skipped in the turn order.
   *
   * @param bool A boolean value indicating whether the entity should skip its turn.
   */
  def skipTurn_=(bool: Boolean): Unit

  /**
   * Accepts a visitor to perform actions on the entity.
   *
   * This method allows an ActionVisitor to interact with the entity. The visitor
   * pattern is used to execute different actions based on the type of the entity
   * without modifying the entity's class.
   *
   * @param visitor The ActionVisitor instance performing actions on the entity.
   */
  def accept(visitor: ActionVisitor): Unit

  /**
   * Casts a spell on the target entity.
   *
   * This method allows the entity to cast a specified spell on another target entity.
   * The effects of the spell will be applied to the target based on the spell's properties.
   *
   * @param target The target entity on which the spell will be cast.
   * @param spell The spell to be cast on the target entity.
   */
  def castSpell(target: Entity, spell: Magic): Unit

  /**
   * Changes the entity's weapon.
   *
   * This method allows the entity to change its current weapon to a new weapon.
   * If None is provided, the entity will be unarmed.
   *
   * @param newWeapon An Option containing the new weapon, or None to indicate no weapon.
   */
  def changeWeapon(newWeapon: Option[Weapon]): Unit

  /**
   * Removes the skip turn status from the entity.
   *
   * This method clears the skip turn status, allowing the entity to act in the next turn.
   */
  def removeSkipTurn(): Unit

  /**
   * Clears all active effects from the entity.
   *
   * This method removes all currently active effects from the entity,
   * resetting its status conditions.
   */
  def clearEffects(): Unit

  /**
   * Receives an attack from another entity.
   *
   * This method handles the logic for when the entity is attacked by another entity.
   * The damage and effects of the attack are applied based on the attacker's properties.
   *
   * @param entity The entity attacking this entity.
   */
  def receiveAttack(entity: Entity): Unit

  /**
   * Performs an attack on an enemy entity.
   *
   * This method allows the entity to perform an attack on a specified enemy entity.
   * The effects and damage of the attack will be applied to the enemy.
   *
   * @param entity The enemy entity being attacked.
   */
  def doAttack(entity: ConcreteEnemy): Unit

  /**
   * Performs an attack on a character entity.
   *
   * This method allows the entity to perform an attack on a specified character entity.
   * The effects and damage of the attack will be applied to the character.
   *
   * @param entity The character entity being attacked.
   */
  def doAttack(entity: Character): Unit

}


