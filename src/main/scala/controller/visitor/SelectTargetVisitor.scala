package controller.visitor

import entity.character.Character
import entity.character.magicCharacter.MagicCharacter
import entity.enemy.Enemy

class SelectTargetVisitor extends ActionVisitor {
  def visitEnemy(enemy: Enemy): Unit = {}
  def visitRegularCharacter(character: Character): Unit = {}
  def visitMagicCharacter(character: MagicCharacter): Unit = {}

}
