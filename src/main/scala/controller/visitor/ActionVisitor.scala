package controller.visitor

import entity.character.magicCharacter.MagicCharacter
import entity.character.Character
import entity.enemy.Enemy

trait ActionVisitor {
  def visitEnemy(enemy: Enemy): List[String]
  def visitRegularCharacter(character: Character): List[String]
  def visitMagicCharacter(character: MagicCharacter): List[String]
}
