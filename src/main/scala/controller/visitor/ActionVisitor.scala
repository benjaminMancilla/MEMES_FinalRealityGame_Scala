package controller.visitor

import entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import entity.character.Character
import entity.enemy.Enemy

trait ActionVisitor {
  def visitEnemy(enemy: Enemy): Unit
  def visitRegularCharacter(character: Character): Unit
  def visitMagicCharacter(character: MagicCharacter): Unit
  def visitBlackMagicCharacter(character: BlackMage): Unit
  def visitWhiteMagicCharacter(character: WhiteMage): Unit
  def buffer : List[String]

}
