package controller.visitor

import model.entity.character.Character
import model.entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import model.entity.enemy.Enemy

/**
 * Concrete implementation of ActionVisitor that defines actions available for different types of characters in a game.
 * It maintains a buffer to store available actions based on the type of character visited.
 */
class GeneralActionVisitor extends ActionVisitor {

  /**
   * Buffer to store available actions or messages based on visited characters.
   */
  var buffer: List[String] = List.empty[String]

  /**
   * Visits an Enemy entity and resets the buffer to an empty list.
   *
   * @param enemy The Enemy entity to visit.
   */
  def visitEnemy(enemy: Enemy): Unit = {
    buffer = List.empty[String]
  }

  /**
   * Visits a regular Character entity and sets the buffer with available actions: Attack, Weapon, Pass.
   *
   * @param character The regular Character entity to visit.
   */
  def visitRegularCharacter(character: Character): Unit = {
    buffer = List("Attack", "Weapon", "Pass")
  }

  /**
   * Visits a Magic Character entity and sets the buffer with available actions: Attack, Weapon, Pass, Magic.
   *
   * @param character The Magic Character entity to visit.
   */
  def visitMagicCharacter(character: MagicCharacter): Unit = {
    buffer = List("Attack", "Weapon", "Pass", "Magic")
  }

  /**
   * Visits a Black Magic Character entity and delegates to visitMagicCharacter for action buffer setup.
   *
   * @param character The Black Magic Character entity to visit.
   */
  def visitBlackMagicCharacter(character: BlackMage): Unit = {
    visitMagicCharacter(character)
  }

  /**
   * Visits a White Magic Character entity and delegates to visitMagicCharacter for action buffer setup.
   *
   * @param character The White Magic Character entity to visit.
   */
  def visitWhiteMagicCharacter(character: WhiteMage): Unit = {
    visitMagicCharacter(character)
  }

}

