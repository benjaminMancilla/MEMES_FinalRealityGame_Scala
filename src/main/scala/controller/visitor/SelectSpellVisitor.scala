package controller.visitor

import exceptions.stateE.InvalidSpellSelector
import model.entity.character.Character
import model.entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import model.entity.enemy.Enemy

/**
 * Implementation of ActionVisitor that visits different character states to determine available actions.
 * The buffer stores the available actions based on the state of the character or enemy.
 */
class SelectSpellVisitor extends ActionVisitor {
  /**
   * Buffer to store available spells or messages based on visited characters.
   */
  var buffer: List[String] = List.empty[String]

  /**
   * Throws an InvalidSpellSelector exception since enemies do not have magic spells.
   *
   * @param enemy The Enemy entity being visited.
   * @throws InvalidSpellSelector Always thrown to indicate enemies do not have magic.
   */
  def visitEnemy(enemy: Enemy): Unit = {
    throw new InvalidSpellSelector(s"${enemy.name} is an enemy, does not have magic.")
  }

  /**
   * Throws an InvalidSpellSelector exception since regular characters do not have magic spells.
   *
   * @param character The regular Character entity being visited.
   * @throws InvalidSpellSelector Always thrown to indicate regular characters do not have magic.
   */
  def visitRegularCharacter(character: Character): Unit = {
    throw new InvalidSpellSelector(s"${character.name} is a common character, does not have magic.")
  }

  /**
   * Throws an InvalidSpellSelector exception since unknown magic characters do not have defined spells.
   *
   * @param character The unknown Magic Character entity being visited.
   * @throws InvalidSpellSelector Always thrown to indicate unknown magic characters do not have defined spells.
   */
  def visitMagicCharacter(character: MagicCharacter): Unit = {
    throw new InvalidSpellSelector(s"${character.name} is a unknown magic character, does not known have magic.")
  }

  /**
   * Sets the buffer with available black magic spells for a Black Magic Character: Fire, Thunder.
   *
   * @param character The Black Magic Character entity being visited.
   */
  def visitBlackMagicCharacter(character: BlackMage): Unit = {
    buffer = List("Fire", "Thunder")
  }

  /**
   * Sets the buffer with available white magic spells for a White Magic Character: Poison, Paralyze, Heal.
   *
   * @param character The White Magic Character entity being visited.
   */
  def visitWhiteMagicCharacter(character: WhiteMage): Unit = {
    buffer = List("Poison", "Paralyze", "Heal")
  }

}


