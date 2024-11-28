package controller.visitor

import model.entity.character.Character
import model.entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import model.entity.enemy.Enemy

/**
 * Implementation of ActionVisitor that visits different character states to determine available actions.
 * The buffer stores the available actions based on the state of the character or enemy.
 */
class StateVisitor extends ActionVisitor {
  var buffer: List[String] = List.empty[String]

  /**
   * Visits an Enemy instance to determine available actions based on its current state.
   *
   * @param enemy The Enemy instance being visited.
   */
  def visitEnemy(enemy: Enemy): Unit = {
    if (enemy.state) {
      buffer = List("E1") // Example action when enemy state is true
    } else {
      buffer = List("E0") // Example action when enemy state is false
    }
  }

  /**
   * Visits a Regular Character instance to determine available actions based on its current state.
   *
   * @param character The Regular Character instance being visited.
   */
  def visitRegularCharacter(character: Character): Unit = {
    if (character.state) {
      buffer = List("C1") // Example action when character state is true
    } else {
      buffer = List("C0") // Example action when character state is false
    }
  }

  /**
   * Visits a Magic Character instance by treating it as a Regular Character instance.
   *
   * @param character The Magic Character instance being visited.
   */
  def visitMagicCharacter(character: MagicCharacter): Unit = {
    visitRegularCharacter(character: Character)
  }

  /**
   * Visits a Black Magic Character instance by treating it as a Regular Character instance.
   *
   * @param character The Black Magic Character instance being visited.
   */
  def visitBlackMagicCharacter(character: BlackMage): Unit = {
    visitRegularCharacter(character: Character)
  }

  /**
   * Visits a White Magic Character instance by treating it as a Regular Character instance.
   *
   * @param character The White Magic Character instance being visited.
   */
  def visitWhiteMagicCharacter(character: WhiteMage): Unit = {
    visitRegularCharacter(character: Character)
  }
}

