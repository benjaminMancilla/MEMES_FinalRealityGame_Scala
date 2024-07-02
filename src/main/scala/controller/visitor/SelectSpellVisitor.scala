package controller.visitor

import entity.character.Character
import entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import entity.enemy.Enemy
import exceptions.stateE.InvalidSpellSelector

class SelectSpellVisitor extends ActionVisitor {
  var buffer: List[String] = List.empty[String]

  def visitEnemy(enemy: Enemy): Unit = {
    throw new InvalidSpellSelector(s"${enemy.name} is an enemy, does not have magic.")
  }

  def visitRegularCharacter(character: Character): Unit = {
    throw new InvalidSpellSelector(s"${character.name} is a common character, does not have magic.")
  }

  def visitMagicCharacter(character: MagicCharacter): Unit = {
    throw new InvalidSpellSelector(s"${character.name} is a unknown magic character, does not known have magic.")
  }

  def visitBlackMagicCharacter(character: BlackMage): Unit = {
    buffer = List("Fire", "Thunder")
  }

  def visitWhiteMagicCharacter(character: WhiteMage): Unit = {
    buffer = List("Poison", "Paralyze", "Heal")
  }

}
