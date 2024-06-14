package magic

import entity.Entity
import entity.character.magicCharacter.{BlackMage, WhiteMage}

/**
 * Represents magicE spells in the game (Public).
 *
 * The `PIMagic` trait defines the common interface for all magicE spells, which can be used by
 * characters that have access to magicE. Magic spells can be applied to a target entityE
 * and have associated properties like name and mana cost. The applySpell is not included due to its
 * effects on the game.
 */
trait PIMagic {


  /**
   * The name of the spell.
   *
   * @return The name of the spell as a string.
   */
  def name: String

  /**
   * The mana cost of the spell.
   *
   * @return The number of magicE points deducted from the sorcerer when the spell is cast.
   */
  def manaCost: Int

  /**
   * Checks if a BlackMage can cast the spell.
   *
   * This method should raise an exception if the BlackMage attempts to use a type of magicE
   * that is not compatible with their class.
   *
   * @param sorcerer The BlackMage attempting to cast the spell.
   * @throws InvalidMagicException if the BlackMage cannot cast the spell.
   */
  def checkSorcerer(sorcerer: BlackMage): Unit

  /**
   * Checks if a WhiteMage can cast the spell.
   *
   * This method should raise an exception if the WhiteMage attempts to use a type of magicE
   * that is not compatible with their class.
   *
   * @param sorcerer The WhiteMage attempting to cast the spell.
   * @throws InvalidMagicException if the WhiteMage cannot cast the spell.
   */
  def checkSorcerer(sorcerer: WhiteMage): Unit

  /**
   * Checks if the target is valid for the spell.
   *
   * This method should raise an exception if the spell is being cast on an invalid target,
   * such as a defensive spell on an enemy or an offensive spell on an ally.
   *
   * @param target The entityE that the spell is being cast on.
   * @throws InvalidTargetException if the target is not valid for the spell.
   */
  def checkTarget(target: Entity): Unit
}
