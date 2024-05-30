package magic

import entity.Entity
import entity.character.{BlackMage, MagicCharacter, WhiteMage}


/**
 * Represents magic spells in the game.
 *
 * The `Magic` trait defines the common interface for all magic spells, which can be used by
 * characters that have access to magic. Magic spells can be applied to a target entity
 * and have associated properties like name and mana cost.
 */
trait Magic {

  /**
   * Applies the effect of the spell to the target.
   *
   * @param sorcerer The magic character casting the spell.
   * @param target The entity that the spell is being cast on.
   * @param mDmg The magic damage or effect value of the spell.
   */
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit

  /**
   * The name of the spell.
   *
   * @return The name of the spell as a string.
   */
  def name: String

  /**
   * The mana cost of the spell.
   *
   * @return The number of magic points deducted from the sorcerer when the spell is cast.
   */
  def manaCost: Int

  /**
   * Checks if a BlackMage can cast the spell.
   *
   * This method should raise an exception if the BlackMage attempts to use a type of magic
   * that is not compatible with their class.
   *
   * @param sorcerer The BlackMage attempting to cast the spell.
   * @throws InvalidMagicException if the BlackMage cannot cast the spell.
   */
  def checkSorcerer(sorcerer: BlackMage): Unit

  /**
   * Checks if a WhiteMage can cast the spell.
   *
   * This method should raise an exception if the WhiteMage attempts to use a type of magic
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
   * @param target The entity that the spell is being cast on.
   * @throws InvalidTargetException if the target is not valid for the spell.
   */
  def checkTarget(target: Entity): Unit
}

