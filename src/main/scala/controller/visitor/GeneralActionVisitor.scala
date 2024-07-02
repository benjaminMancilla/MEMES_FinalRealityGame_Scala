package controller.visitor

import entity.character.Character
import entity.character.magicCharacter.MagicCharacter
import entity.enemy.Enemy

class GeneralActionVisitor extends ActionVisitor {
  var buffer: List[String] = List.empty[String]

  def visitEnemy(enemy: Enemy): Unit = {
    buffer = List.empty[String]
  }

  def visitRegularCharacter(character: Character): Unit = {
    buffer = List("Attack", "Weapon", "Pass")
  }

  def visitMagicCharacter(character: MagicCharacter): Unit = {
    buffer = List("Attack", "Weapon", "Pass", "Magic")
  }
}
