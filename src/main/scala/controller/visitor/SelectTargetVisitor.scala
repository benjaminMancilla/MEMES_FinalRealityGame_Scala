package controller.visitor

import entity.character.Character
import entity.character.magicCharacter.MagicCharacter
import entity.enemy.Enemy

class SelectTargetVisitor extends ActionVisitor {
  var buffer: List[String] = List[String].empty
  def visitEnemy(enemy: Enemy): Unit = {

  }
  def visitRegularCharacter(character: Character): Unit = { buffer = List[String]("Attack", "Weapon", "Pass")}
  def visitMagicCharacter(character: MagicCharacter): Unit = { buffer = List[String]("Attack", "Weapon", "Pass", "Magic")}

}
