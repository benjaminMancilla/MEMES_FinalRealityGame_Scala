package controller.visitor

import model.entity.character.Character
import model.entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import model.entity.enemy.Enemy

/**
 * Trait defining a visitor pattern for visiting different types of characters in a game.
 * Each method corresponds to visiting a specific character subclass implementing the Entity trait.
 */
trait ActionVisitor {

  /**
   * Visits an Enemy entity and performs specific actions associated with this type of entity.
   *
   * @param enemy The Enemy entity to visit.
   */
  def visitEnemy(enemy: Enemy): Unit

  /**
   * Visits a regular Character entity and performs specific actions associated with this type of entity.
   *
   * @param character The regular Character entity to visit.
   */
  def visitRegularCharacter(character: Character): Unit

  /**
   * Visits a Magic Character entity and performs specific actions associated with this type of entity.
   *
   * @param character The Magic Character entity to visit.
   */
  def visitMagicCharacter(character: MagicCharacter): Unit

  /**
   * Visits a Black Magic Character entity and performs specific actions associated with this type of entity.
   *
   * @param character The Black Magic Character entity to visit.
   */
  def visitBlackMagicCharacter(character: BlackMage): Unit

  /**
   * Visits a White Magic Character entity and performs specific actions associated with this type of entity.
   *
   * @param character The White Magic Character entity to visit.
   */
  def visitWhiteMagicCharacter(character: WhiteMage): Unit

  /**
   * Buffer for storing visitation results or messages.
   */
  def buffer: List[String]
}

