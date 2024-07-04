package controller.visitor

import model.entity.character.Character
import model.entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import model.entity.enemy.Enemy

class StateVisitor extends ActionVisitor {
  var buffer: List[String] = List.empty[String]

  def visitEnemy(enemy: Enemy): Unit = {
    if (enemy.state){
      buffer = List("E1")
    }
    else {
      buffer = List("E0")
    }

  }

  def visitRegularCharacter(character: Character): Unit = {
    if (character.state){
      buffer = List("C1")
    }
    else {
      buffer = List("C0")
    }
  }

  def visitMagicCharacter(character: MagicCharacter): Unit = {
    visitRegularCharacter(character: Character)
  }

  def visitBlackMagicCharacter(character: BlackMage): Unit = {
    visitRegularCharacter(character: Character)
  }

  def visitWhiteMagicCharacter(character: WhiteMage): Unit = {
    visitRegularCharacter(character: Character)
  }

}
