package controller.visitor

import entity.character.magicCharacter.MagicCharacter
import entity.character.Character
import entity.enemy.Enemy

trait ActionVisitor {
  def visitEnemy(enemy: Enemy): Unit
  def visitRegularCharacter(character: Character): Unit
  def visitMagicCharacter(character: MagicCharacter): Unit
  def buffer : List[String]
}
