package magic

import entity.character.magicCharacter.{BlackMage, WhiteMage}
import exceptions.magicE.InvalidMagicType


/**
 * Represents an abstract magicE spell in the game.
 *
 * The `AbstractMagic` class extends the `Magic` trait and provides common functionality for all magicE spells.
 * It defines properties like name and mana cost, and implements methods for checking compatibility with sorcerers.
 */
abstract class AbstractMagic extends Magic {

  /**
   * The name of the magicE spell.
   */
  val _name: String

  /**
   * The mana cost of the magicE spell.
   */
  val _manaCost: Int

  /**
   * Returns the name of the magicE spell.
   *
   * @return The name of the magicE spell.
   */
  def name: String = _name

  /**
   * Returns the mana cost of the magicE spell.
   *
   * @return The mana cost of the magicE spell.
   */
  def manaCost: Int = _manaCost

  /**
   * Checks if a BlackMage can cast the spell.
   *
   * This method throws an exception indicating that the spell is not compatible with black magicE.
   *
   * @param sorcerer The BlackMage attempting to cast the spell.
   * @throws InvalidMagicType if the spell is not compatible with black magicE.
   */
  def checkSorcerer(sorcerer: BlackMage): Unit = {
    throw new InvalidMagicType(s"${this.name} spell is not compatible with ${sorcerer.name} black magicE")
  }

  /**
   * Checks if a WhiteMage can cast the spell.
   *
   * This method throws an exception indicating that the spell is not compatible with white magicE.
   *
   * @param sorcerer The WhiteMage attempting to cast the spell.
   * @throws InvalidMagicType if the spell is not compatible with white magicE.
   */
  def checkSorcerer(sorcerer: WhiteMage): Unit = {
    throw new InvalidMagicType(s"${this.name} spell is not compatible with ${sorcerer.name} white magicE")
  }

}

