package controller.visitor

import model.entity.character.Character
import model.entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import model.entity.enemy.Enemy

trait ActionVisitor {
  def visitEnemy(enemy: Enemy): Unit
  def visitRegularCharacter(character: Character): Unit
  def visitMagicCharacter(character: MagicCharacter): Unit
  def visitBlackMagicCharacter(character: BlackMage): Unit
  def visitWhiteMagicCharacter(character: WhiteMage): Unit
  def buffer : List[String]

}
